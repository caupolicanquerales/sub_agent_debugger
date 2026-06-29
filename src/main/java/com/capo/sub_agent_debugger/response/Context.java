package com.capo.sub_agent_debugger.response;

public record Context(Integer startLine, Integer endLine, Integer targetLine, String codeSnippet) {

}
