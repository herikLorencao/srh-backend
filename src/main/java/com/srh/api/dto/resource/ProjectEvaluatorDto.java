package com.srh.api.dto.resource;

import com.srh.api.model.ProjectEvaluator;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "relationship")
public class ProjectEvaluatorDto {
    private final Integer projectId;
    private final Integer recommenderId;

    public ProjectEvaluatorDto(ProjectEvaluator projectEvaluator) {
        this.projectId = projectEvaluator.getProject().getId();
        this.recommenderId = projectEvaluator.getEvaluator().getId();
    }
}
