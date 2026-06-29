package com.capo.sub_agent_debugger.response;

import java.util.List;

public record DataStepPlanError(String errorSummary, String detectedLanguage, String detectedFramework, List<String> errorType, List<Step> steps) {

}
