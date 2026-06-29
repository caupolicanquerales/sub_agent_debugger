package com.capo.sub_agent_debugger.response;

public record Payload(String command, String cwd, String filepath, String pathType, String line, String context, String insertionMode, String find, String replace, String instruction) {

}
