package com.capo.sub_agent_debugger.response;

import java.util.List;

public record DataEditDefect(String id, String filepath, String status, String explanation,
		List<EditDefect> edits) {

}
