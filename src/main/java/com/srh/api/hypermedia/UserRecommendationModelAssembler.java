package com.srh.api.hypermedia;

import com.srh.api.controller.UserRecommendationController;
import com.srh.api.dto.resource.UserRecommendationDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserRecommendationModelAssembler implements RepresentationModelAssembler<UserRecommendationDto, EntityModel<UserRecommendationDto>> {
    @Override
    public EntityModel<UserRecommendationDto> toModel(UserRecommendationDto userRecommendationDto) {
        return new EntityModel<>(userRecommendationDto,
                linkTo(methodOn(UserRecommendationController.class).find(userRecommendationDto.getId())).withSelfRel(),
                linkTo(UserRecommendationController.class).withRel("/users/recommenders")
        );
    }
}
