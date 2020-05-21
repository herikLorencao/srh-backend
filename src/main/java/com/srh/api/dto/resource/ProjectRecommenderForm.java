package com.srh.api.dto.resource;

import javax.validation.constraints.NotNull;

public class ProjectRecommenderForm {
    @NotNull
    private Integer projectId;
    @NotNull
    private Integer recommenderId;

    public ProjectRecommenderForm() {
    }

    public ProjectRecommenderForm(@NotNull Integer projectId, @NotNull Integer recommenderId) {
        this.projectId = projectId;
        this.recommenderId = recommenderId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public Integer getRecommenderId() {
        return recommenderId;
    }
}
