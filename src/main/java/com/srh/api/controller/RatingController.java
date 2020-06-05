package com.srh.api.controller;

import com.srh.api.dto.resource.RatingDto;
import com.srh.api.dto.resource.RatingForm;
import com.srh.api.hypermedia.RatingModelAssembler;
import com.srh.api.model.ItemRating;
import com.srh.api.service.RatingService;
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

import static com.srh.api.dto.resource.RatingDto.convert;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingModelAssembler ratingModelAssembler;

    @Autowired
    private PagedResourcesAssembler<RatingDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<RatingDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                              Pageable pageInfo) {
        Page<ItemRating> ratings = ratingService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(ratings));
    }

    @GetMapping("/{id}")
    public EntityModel<RatingDto> find(@PathVariable Integer id) {
        ItemRating itemRating = ratingService.find(id);
        return ratingModelAssembler.toModel(new RatingDto(itemRating));
    }

    @PostMapping
    public ResponseEntity<EntityModel<RatingDto>> create(@RequestBody @Valid RatingForm ratingForm,
                                                         UriComponentsBuilder uriBuilder) {
        ItemRating itemRating = ratingForm.build();
        ratingService.save(itemRating);
        URI uri = uriBuilder.path("/ratings/{id}").buildAndExpand(itemRating.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(ratingModelAssembler.toModel(new RatingDto(itemRating)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<RatingDto> update(@RequestBody @Valid RatingForm ratingForm,
                                         @PathVariable Integer id) {
        ItemRating itemRating = ratingForm.build();
        itemRating.setId(id);
        itemRating = ratingService.update(itemRating);
        return ratingModelAssembler.toModel(new RatingDto(itemRating));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ratingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
