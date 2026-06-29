package com.capo.sub_agent_debugger.response;

public class DataMessage {
	
	private String message;
	private String type;
	private DataToolCall toolCall;
	private DataStepPlanError stepPlanError;
	private DataDefects defects;
	private DataEditDefect editDefect;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DataToolCall getToolCall() {
		return toolCall;
	}

	public void setToolCall(DataToolCall toolCall) {
		this.toolCall = toolCall;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DataStepPlanError getStepPlanError() {
		return stepPlanError;
	}

	public void setStepPlanError(DataStepPlanError stepPlanError) {
		this.stepPlanError = stepPlanError;
	}

	public DataDefects getDefects() {
		return defects;
	}

	public void setDefects(DataDefects defects) {
		this.defects = defects;
	}

	public DataEditDefect getEditDefect() {
		return editDefect;
	}

	public void setEditDefect(DataEditDefect editDefect) {
		this.editDefect = editDefect;
	}
	
}
