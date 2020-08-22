package com.srh.api.controller;

import com.srh.api.dto.resource.ItemRatingDto;
import com.srh.api.dto.resource.ItemRatingForm;
import com.srh.api.hypermedia.ItemRatingModelAssembler;
import com.srh.api.model.ItemRating;
import com.srh.api.service.ItemRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

import java.net.URI;

import static com.srh.api.dto.resource.ItemRatingDto.convert;

@RestController
@RequestMapping("/itemratings")
public class ItemRatingController {
    @Autowired
    private ItemRatingService itemRatingService;

    @Autowired
    private ItemRatingModelAssembler itemRatingModelAssembler;

    @Autowired
    PagedResourcesAssembler<ItemRatingDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<ItemRatingDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                                  Pageable pageInfo) {
        Page<ItemRating> itemRatings = itemRatingService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(itemRatings));
    }

    @GetMapping("/{id}")
    public EntityModel<ItemRatingDto> find(@PathVariable Integer id) {
        ItemRating itemRating = itemRatingService.find(id);
        return itemRatingModelAssembler.toModel(new ItemRatingDto(itemRating));
    }

    @PostMapping
    public ResponseEntity<EntityModel<ItemRatingDto>> create(@RequestBody @Valid ItemRatingForm itemRatingForm,
                                                             UriComponentsBuilder uriBuilder) {
        ItemRating itemRating = itemRatingForm.build();
        itemRatingService.save(itemRating);
        URI uri = uriBuilder.path("/itemratings/{id}").buildAndExpand(itemRating.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(itemRatingModelAssembler.toModel(new ItemRatingDto(itemRating)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<ItemRatingDto> update(@RequestBody @Valid ItemRatingForm itemRatingForm,
                                             @PathVariable Integer id) {
        ItemRating itemRating = itemRatingForm.build();
        itemRating.setId(id);
        itemRating = itemRatingService.update(itemRating);
        return itemRatingModelAssembler.toModel(new ItemRatingDto(itemRating));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        itemRatingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/itens/{itemId}")
    public ResponseEntity<Page<ItemRatingDto>> listItensRatingsByItem(@PathVariable Integer itemId, Pageable pageInfo) {
        Page<ItemRating> itensRatings = itemRatingService.listItensRatingsByItem(itemId, pageInfo);
        return ResponseEntity.ok(ItemRatingDto.convert(itensRatings));
    }

    @GetMapping("/tags/{tagId}")
    public ResponseEntity<Page<ItemRatingDto>> listItensRatingsByTag(@PathVariable Integer tagId,
                                                                     Pageable pageInfo) {
        Page<ItemRating> itensRatings = itemRatingService.listItensRAtingsByTag(tagId, pageInfo);
        return ResponseEntity.ok(ItemRatingDto.convert(itensRatings));
    }
}
