package com.srh.api.controller;

import com.srh.api.dto.resource.RatingDto;
import com.srh.api.dto.resource.RatingForm;
import com.srh.api.hypermedia.RatingModelAssembler;
import com.srh.api.model.Rating;
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
    public PagedModel<EntityModel<RatingDto>> listAll(@PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<Rating> ratings = ratingService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(ratings));
    }

    @GetMapping("/{id}")
    public EntityModel<RatingDto> find(@PathVariable Integer id) {
        Rating rating = ratingService.find(id);
        return ratingModelAssembler.toModel(new RatingDto(rating));
    }

    @PostMapping
    public ResponseEntity<EntityModel<RatingDto>> create(@RequestBody @Valid RatingForm ratingForm,
                                                         UriComponentsBuilder uriBuilder) {
        Rating rating = ratingForm.build();
        ratingService.save(rating);
        URI uri = uriBuilder.path("/ratings/{id}").buildAndExpand(rating.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(ratingModelAssembler.toModel(new RatingDto(rating)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<RatingDto> update(@RequestBody @Valid RatingForm ratingForm, @PathVariable Integer id) {
        Rating rating = ratingForm.build();
        rating.setId(id);
        rating = ratingService.update(rating);
        return ratingModelAssembler.toModel(new RatingDto(rating));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ratingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
