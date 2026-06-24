package com.capo.sub_agent_debugger.request;

import java.util.List;

public class GenerationSyntheticDataRequest {
	
	private String prompt;
	private String conversationId;
	private ToolIformation toolRequest;
	private List<String> imageReferences;

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public ToolIformation getToolRequest() {
		return toolRequest;
	}

	public void setToolRequest(ToolIformation toolRequest) {
		this.toolRequest = toolRequest;
	}

	public List<String> getImageReferences() {
		return imageReferences;
	}

	public void setImageReferences(List<String> imageReferences) {
		this.imageReferences = imageReferences;
	}
	
}
