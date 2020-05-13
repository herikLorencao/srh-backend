package com.srh.api.hypermedia;

import com.srh.api.controller.ItemController;
import com.srh.api.dto.resource.ItemDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemModelAssembler implements RepresentationModelAssembler<ItemDto, EntityModel<ItemDto>> {
    @Override
    public EntityModel<ItemDto> toModel(ItemDto itemDto) {
        return new EntityModel<>(itemDto,
                linkTo(methodOn(ItemController.class).find(itemDto.getId())).withSelfRel(),
                linkTo(ItemController.class).withRel("itens")
        );
    }
}
