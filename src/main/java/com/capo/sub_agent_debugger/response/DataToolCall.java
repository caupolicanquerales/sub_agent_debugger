package com.capo.sub_agent_debugger.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataToolCall(String name, @JsonAlias("argument") List<ProjectName> arguments) {
	
}
