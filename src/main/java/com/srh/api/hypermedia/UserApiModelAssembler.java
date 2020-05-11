package com.srh.api.hypermedia;

import com.srh.api.controller.UserApiController;
import com.srh.api.dto.resource.UserApiDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserApiModelAssembler implements RepresentationModelAssembler<UserApiDto, EntityModel<UserApiDto>> {
    @Override
    public EntityModel<UserApiDto> toModel(UserApiDto userApiDto) {
        return new EntityModel<>(userApiDto,
                linkTo(methodOn(UserApiController.class).find(userApiDto.getId())).withSelfRel(),
                linkTo(UserApiController.class).withRel("/users/api")
        );
    }
}
