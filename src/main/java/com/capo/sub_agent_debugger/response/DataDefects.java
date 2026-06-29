package com.capo.sub_agent_debugger.response;

import java.util.List;

public record DataDefects(Integer totalDefectsFound, List<Defect> defects) {

}
