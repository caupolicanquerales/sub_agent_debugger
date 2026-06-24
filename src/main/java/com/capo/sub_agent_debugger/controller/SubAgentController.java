package com.capo.sub_agent_debugger.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.capo.sub_agent_debugger.request.GenerationSyntheticDataRequest;
import com.capo.sub_agent_debugger.service.ExecutingDebuggerService;
import com.capo.sub_agent_debugger.utils.SseStreamUtil;


@RestController
@RequestMapping("sub-agent-debugger")
public class SubAgentController {
	
	private final ExecutorService executor = Executors.newCachedThreadPool();
	private final ExecutingDebuggerService executingDebugger;
	
	@Value(value="${event.name.chat}")
	private String eventName;
	
	public SubAgentController(ExecutingDebuggerService executingDebugger) {
		this.executingDebugger= executingDebugger;
	}
	
	@PostMapping(path = "/chat-stream-debugger", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamDebuggerGeneration(@RequestBody GenerationSyntheticDataRequest request) {
		return SseStreamUtil.stream(executor, eventName, "Executing debugger the logs",
                () -> executingDebugger.generateExtractingOrderCommandAsync(
                        request.getPrompt(), request.getImageReferences()),
                result -> {
                    return result;
                });
	}
}
