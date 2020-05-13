package com.srh.api.hypermedia;

import com.srh.api.controller.TypeRecommendationController;
import com.srh.api.dto.resource.TypeRecommendationDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TypeRecommendationModelAssembler implements RepresentationModelAssembler<TypeRecommendationDto, EntityModel<TypeRecommendationDto>> {
    @Override
    public EntityModel<TypeRecommendationDto> toModel(TypeRecommendationDto typeRecommendationDto) {
        return new EntityModel<>(typeRecommendationDto,
                linkTo(methodOn(TypeRecommendationController.class).find(typeRecommendationDto.getId())).withSelfRel(),
                linkTo(TypeRecommendationController.class).withRel("recommendations/types")
        );
    }
}
