package com.srh.api.hypermedia;

import com.srh.api.controller.ApiUsersController;
import com.srh.api.dto.resource.ApiUsersDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ApiUserModelAssembler implements RepresentationModelAssembler<ApiUsersDto, EntityModel<ApiUsersDto>> {
    @Override
    public EntityModel<ApiUsersDto> toModel(ApiUsersDto apiUsersDto) {
        return new EntityModel<>(apiUsersDto,
                linkTo(methodOn(ApiUsersController.class).find(apiUsersDto.getId())).withSelfRel(),
                linkTo(ApiUsersController.class).withRel("/users/apis")
        );
    }
}
