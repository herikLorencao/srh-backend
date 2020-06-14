package com.srh.api.dto.resource;

import com.srh.api.model.ProjectRecommender;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "relationship")
public class ProjectEvaluatorDto {
    private final Integer projectId;
    private final Integer recommenderId;

    public ProjectEvaluatorDto(ProjectRecommender projectRecommender) {
        this.projectId = projectRecommender.getProject().getId();
        this.recommenderId = projectRecommender.getEvaluator().getId();
    }
}
