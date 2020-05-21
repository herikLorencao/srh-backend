package com.srh.api.hypermedia;

import com.srh.api.controller.ProjectRecommenderController;
import com.srh.api.dto.resource.ProjectRecommenderDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjectRecommenderModelAssembler implements RepresentationModelAssembler<ProjectRecommenderDto, EntityModel<ProjectRecommenderDto>> {
    @Override
    public EntityModel<ProjectRecommenderDto> toModel(ProjectRecommenderDto projectRecommender) {
        return new EntityModel<>(projectRecommender,
                linkTo(methodOn(ProjectRecommenderController.class).
                        listRecommendersByProject(projectRecommender.getProjectId(), Pageable.unpaged()))
                        .withRel("recommendersInProject")
        );
    }
}
