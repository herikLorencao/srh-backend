package com.srh.api.dto.resource;

import com.srh.api.model.ProjectRecommender;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "relationship")
public class ProjectRecommenderDto {
    private final Integer projectId;
    private final Integer recommenderId;

    public ProjectRecommenderDto(ProjectRecommender projectRecommender) {
        this.projectId = projectRecommender.getProject().getId();
        this.recommenderId = projectRecommender.getRecommender().getId();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public Integer getRecommenderId() {
        return recommenderId;
    }
}
