package com.srh.api.hypermedia;

import com.srh.api.controller.AdminController;
import com.srh.api.dto.resource.AdminDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AdminModelAssembler implements RepresentationModelAssembler<AdminDto, EntityModel<AdminDto>> {
    @Override
    public EntityModel<AdminDto> toModel(AdminDto adminDto) {
        return new EntityModel<>(adminDto,
                linkTo(methodOn(AdminController.class).find(adminDto.getId())).withSelfRel(),
                linkTo(AdminController.class).withRel("/users/admin")
        );
    }
}
