package com.srh.api.hypermedia;

import com.srh.api.controller.RatingController;
import com.srh.api.dto.resource.RatingDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RatingModelAssembler implements RepresentationModelAssembler<RatingDto, EntityModel<RatingDto>> {
    @Override
    public EntityModel<RatingDto> toModel(RatingDto itemRatingDto) {
        return new EntityModel<>(itemRatingDto,
                linkTo(methodOn(RatingController.class).find(itemRatingDto.getId())).withSelfRel(),
                linkTo(RatingController.class).withRel("ratings")
        );
    }
}
