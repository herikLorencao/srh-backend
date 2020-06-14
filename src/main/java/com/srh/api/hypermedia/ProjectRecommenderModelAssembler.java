package com.srh.api.hypermedia;

import com.srh.api.controller.ProjectController;
import com.srh.api.controller.ProjectEvaluatorController;
import com.srh.api.controller.EvaluatorController;
import com.srh.api.dto.resource.ProjectEvaluatorDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjectRecommenderModelAssembler implements RepresentationModelAssembler<ProjectEvaluatorDto, EntityModel<ProjectEvaluatorDto>> {
    @Override
    public EntityModel<ProjectEvaluatorDto> toModel(ProjectEvaluatorDto projectRecommender) {
        return new EntityModel<>(projectRecommender,
                linkTo(methodOn(ProjectEvaluatorController.class).
                        findRecommenderInProject(projectRecommender.getProjectId(),
                                projectRecommender.getRecommenderId())).withSelfRel(),
                linkTo(methodOn(ProjectEvaluatorController.class).
                        listRecommendersByProject(projectRecommender.getProjectId(), Pageable.unpaged()))
                        .withRel("recommendersInProject"),
                linkTo(methodOn(ProjectController.class).find(projectRecommender.getProjectId()))
                        .withRel("project"),
                linkTo(methodOn(EvaluatorController.class).find(projectRecommender.getRecommenderId()))
                        .withRel("recommender")
        );
    }
}
