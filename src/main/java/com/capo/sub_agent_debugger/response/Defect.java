package com.capo.sub_agent_debugger.response;

public record Defect(String id, String severity, String category, String title, 
		String description, Coordinates coordinates, Context context) {

}
