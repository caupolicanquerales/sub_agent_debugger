package com.capo.sub_agent_debugger.response;

public record EditDefect(String action, Integer startLine, Integer startColumn, Integer endLine,
		Integer endColum, String content) {

}
