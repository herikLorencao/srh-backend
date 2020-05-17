package com.srh.api.controller;

import com.srh.api.dto.resource.RecommendationDto;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.hypermedia.RecommendationModelAssembler;
import com.srh.api.model.Recommendation;
import com.srh.api.service.RecommendationService;
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

import static com.srh.api.dto.resource.RecommendationDto.convert;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private RecommendationModelAssembler recommendationModelAssembler;

    @Autowired
    private PagedResourcesAssembler<RecommendationDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<RecommendationDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                                      Pageable pageInfo) {
        Page<Recommendation> recommendations = recommendationService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(recommendations));
    }

    @GetMapping("/{id}")
    public EntityModel<RecommendationDto> find(@PathVariable Integer id) {
        Recommendation recommendation = recommendationService.find(id);
        return recommendationModelAssembler.toModel(new RecommendationDto(recommendation));
    }

    @PostMapping
    public ResponseEntity<EntityModel<RecommendationDto>> create(
            @RequestBody @Valid RecommendationForm recommendationForm, UriComponentsBuilder uriBuilder) {
        Recommendation recommendation = recommendationForm.build();
        recommendationService.save(recommendation);
        URI uri = uriBuilder.path("/recommendations/{id}").buildAndExpand(recommendation.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(recommendationModelAssembler.toModel(new RecommendationDto(recommendation)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<RecommendationDto> update(
            @RequestBody @Valid RecommendationForm recommendationForm, @PathVariable Integer id) {
        Recommendation recommendation = recommendationForm.build();
        recommendation.setId(id);
        recommendation = recommendationService.update(recommendation);
        return recommendationModelAssembler.toModel(new RecommendationDto(recommendation));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        recommendationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
