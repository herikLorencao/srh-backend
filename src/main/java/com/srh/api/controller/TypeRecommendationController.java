package com.srh.api.controller;

import com.srh.api.dto.resource.TypeRecommendationDto;
import com.srh.api.dto.resource.TypeRecommendationForm;
import com.srh.api.hypermedia.TypeRecommendationModelAssembler;
import com.srh.api.model.TypeRecommendation;
import com.srh.api.service.TypeRecommendationService;
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

import static com.srh.api.dto.resource.TypeRecommendationDto.convert;

@RestController
@RequestMapping("/recommendations/types")
public class TypeRecommendationController {
    @Autowired
    private TypeRecommendationService typeRecommendationService;

    @Autowired
    private TypeRecommendationModelAssembler typeRecommendationModelAssembler;

    @Autowired
    private PagedResourcesAssembler<TypeRecommendationDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<TypeRecommendationDto>> listAll(@PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<TypeRecommendation> typeRecommendations = typeRecommendationService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(typeRecommendations));
    }

    @GetMapping("/{id}")
    public EntityModel<TypeRecommendationDto> find(@PathVariable Integer id) {
        TypeRecommendation typeRecommendation = typeRecommendationService.find(id);
        return typeRecommendationModelAssembler.toModel(new TypeRecommendationDto(typeRecommendation));
    }

    @PostMapping
    public ResponseEntity<EntityModel<TypeRecommendationDto>> create(@RequestBody @Valid TypeRecommendationForm typeRecommendationForm,
                                                                     UriComponentsBuilder uriBuilder) {
        TypeRecommendation typeRecommendation = typeRecommendationForm.build();
        typeRecommendationService.save(typeRecommendation);
        URI uri = uriBuilder.path("/recommendations/types/{id}").buildAndExpand(typeRecommendation.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(typeRecommendationModelAssembler.toModel(new TypeRecommendationDto(typeRecommendation)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<TypeRecommendationDto> update(@RequestBody @Valid TypeRecommendationForm typeRecommendationForm, @PathVariable Integer id) {
        TypeRecommendation typeRecommendation = typeRecommendationForm.build();
        typeRecommendation.setId(id);
        typeRecommendation = typeRecommendationService.update(typeRecommendation);
        return typeRecommendationModelAssembler.toModel(new TypeRecommendationDto(typeRecommendation));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        typeRecommendationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
