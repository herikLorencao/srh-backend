package com.srh.api.hypermedia;

import com.srh.api.controller.AlgorithmController;
import com.srh.api.controller.RecommendationController;
import com.srh.api.controller.RecommendationResultMatrixController;
import com.srh.api.dto.resource.RecommendationResultMatrixDto;
import com.srh.api.model.Recommendation;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecommendationResultMatrixModelAssembler
        implements RepresentationModelAssembler<RecommendationResultMatrixDto,
        EntityModel<RecommendationResultMatrixDto>> {

    @Override
    public EntityModel<RecommendationResultMatrixDto> toModel(
            RecommendationResultMatrixDto recommendationResultMatrixDto) {
        EntityModel entityModel = new EntityModel<>(recommendationResultMatrixDto,
                linkTo(methodOn(RecommendationResultMatrixController.class).find(
                        recommendationResultMatrixDto.getId()
                )).withSelfRel(),
                linkTo(methodOn(RecommendationResultMatrixController.class).listAll(Pageable.unpaged()))
                        .withRel("recommendation matrices"),
                linkTo(methodOn(AlgorithmController.class).find
                        (recommendationResultMatrixDto.getAlgorithm().getId())).withRel("algorithm")
        );

        if (recommendationResultMatrixDto.getRecommendations() != null) {
            entityModel.add(buildProjectsLinks(recommendationResultMatrixDto.getRecommendations()));
        }

        return entityModel;
    }

    private List<Link> buildProjectsLinks(List<Recommendation> recommendations) {
        List<Link> links = new ArrayList<>();

        for (Recommendation recommendation : recommendations) {
            links.add(linkTo(methodOn(RecommendationController.class).find(recommendation.getId()))
                    .withRel("recommendations"));
        }

        return links;
    }
}
