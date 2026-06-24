package com.capo.sub_agent_debugger.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.capo.sub_agent_debugger.response.DataMessage;
import com.capo.sub_agent_debugger.response.DataStepPlanError;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ExecutingDebuggerService {
	
	private final ChatClient chatClient;
	private final String systemPrompt;
	private final ObjectMapper objectMapper;
	private final RedisTemplate<String, Object> redisTemplate;
	private final static String SHOW_VS_CODE="step_actions";
	
	public ExecutingDebuggerService(@Qualifier("chatClientGeneral") ChatClient chatClient,
			@Qualifier("systemPrompt") String systemPrompt,
			ObjectMapper objectMapper,
			RedisTemplate<String, Object> redisTemplate) {
		this.chatClient = chatClient;
		this.systemPrompt = systemPrompt;
		this.objectMapper = objectMapper;
		this.redisTemplate = redisTemplate;
	}
	
	public CompletableFuture<String> generateExtractingOrderCommandAsync(String prompt, List<String> imageReferences) {
		String layoutKey = (imageReferences != null && !imageReferences.isEmpty())
				? imageReferences.get(0) : null;
	
		return CompletableFuture.supplyAsync(() -> {
			String userMessage = "";
			if (layoutKey != null) {
				Object stored = redisTemplate.opsForValue().get(layoutKey);
				if (stored != null) {
					userMessage = userMessage  + stored.toString();
				}
			}
			String content = this.chatClient.prompt()
					.messages(new SystemMessage(systemPrompt))
					.user(userMessage)
					.call()
					.content();

			DataMessage dataMessage = new DataMessage();
			dataMessage.setType(SHOW_VS_CODE);
			try {
				String json = content.trim();
				if (json.startsWith("```")) {
					json = json.replaceAll("```[a-zA-Z]*\\n?", "").replace("```", "").trim();
				}
				DataStepPlanError stepPlan = objectMapper.readValue(json, DataStepPlanError.class);
				dataMessage.setStepPlanError(stepPlan);
			} catch (Exception e) {
				dataMessage.setMessage(content);
			}
			try {
				return objectMapper.writeValueAsString(dataMessage);
			} catch (Exception e) {
				return content;
			}
		});
	} 
}
