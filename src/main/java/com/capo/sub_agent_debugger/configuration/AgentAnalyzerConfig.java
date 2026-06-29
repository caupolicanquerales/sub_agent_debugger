package com.capo.sub_agent_debugger.configuration;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
public class AgentAnalyzerConfig {
	
	@Value("classpath:prompts/system-prompt-analyzer.md")
    private Resource systemPromptResource;
	
	@Bean
    public String systemPromptAnalyzer() throws IOException {
        return systemPromptResource.getContentAsString(Charset.defaultCharset());
    }

	@Bean
    public ChatClient chatClientAnalyzer(ChatClient.Builder builder) {
        return builder
    		.clone()
            .build();
    }

}
