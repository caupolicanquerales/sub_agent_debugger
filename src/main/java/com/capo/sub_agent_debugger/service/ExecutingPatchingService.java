package com.capo.sub_agent_debugger.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.capo.sub_agent_debugger.response.DataEditDefect;
import com.capo.sub_agent_debugger.response.DataMessage;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ExecutingPatchingService {
	
	private final ChatClient chatClient;
	private final String systemPrompt;
	private final ObjectMapper objectMapper;
	
	public ExecutingPatchingService(@Qualifier("chatClientPatching") ChatClient chatClient,
			@Qualifier("systemPromptPatching") String systemPrompt,
			ObjectMapper objectMapper) {
		this.chatClient = chatClient;
		this.systemPrompt = systemPrompt;
		this.objectMapper = objectMapper;
	}
	
	public CompletableFuture<String> generateExtractingOrderCommandAsync(String prompt, List<String> imageReferences) {
	
		return CompletableFuture.supplyAsync(() -> {
			String userMessage = prompt;
			String content = this.chatClient.prompt()
					.messages(new SystemMessage(systemPrompt))
					.user(userMessage)
					.call()
					.content();

			DataMessage dataMessage = new DataMessage();
			try {
				String json = content.trim();
				if (json.startsWith("```")) {
					json = json.replaceAll("```[a-zA-Z]*\\n?", "").replace("```", "").trim();
				}
				DataEditDefect stepEdit = objectMapper.readValue(json, DataEditDefect.class);
				dataMessage.setEditDefect(stepEdit);
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
