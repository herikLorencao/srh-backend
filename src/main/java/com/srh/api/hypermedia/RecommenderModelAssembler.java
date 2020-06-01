package com.srh.api.hypermedia;

import com.srh.api.controller.RecommenderController;
import com.srh.api.dto.resource.RecommenderDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecommenderModelAssembler implements RepresentationModelAssembler<RecommenderDto, EntityModel<RecommenderDto>> {
    @Override
    public EntityModel<RecommenderDto> toModel(RecommenderDto recommenderDto) {
        return new EntityModel<>(recommenderDto,
                linkTo(methodOn(RecommenderController.class).find(recommenderDto.getId())).withSelfRel(),
                linkTo(RecommenderController.class).withRel("recommenders")
        );
    }
}
