package com.srh.api.controller;

import com.srh.api.dto.resource.RecommenderDto;
import com.srh.api.dto.resource.RecommenderForm;
import com.srh.api.hypermedia.RecommenderModelAssembler;
import com.srh.api.model.Recommender;
import com.srh.api.service.RecommenderService;
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

import static com.srh.api.dto.resource.RecommenderDto.convert;

@RestController
@RequestMapping("/users/recommenders")
public class RecommenderController {
    @Autowired
    private RecommenderService recommenderService;

    @Autowired
    private RecommenderModelAssembler recommenderModelAssembler;

    @Autowired
    private PagedResourcesAssembler<RecommenderDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<RecommenderDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                                          Pageable pageInfo) {
        Page<Recommender> users = recommenderService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(users));
    }

    @GetMapping("/{id}")
    public EntityModel<RecommenderDto> find(@PathVariable Integer id) {
        Recommender recommender = recommenderService.find(id);
        return recommenderModelAssembler.toModel(new RecommenderDto(recommender));
    }

    @PostMapping
    public ResponseEntity<EntityModel<RecommenderDto>> create(@RequestBody @Valid RecommenderForm recommenderForm,
                                                              UriComponentsBuilder uriBuilder) {
        Recommender recommender = recommenderForm.build();
        recommenderService.save(recommender);
        URI uri = uriBuilder.path("/users/recommenders/{id}").buildAndExpand(recommender.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(recommenderModelAssembler.toModel(new RecommenderDto(recommender)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<RecommenderDto> update(@RequestBody @Valid RecommenderForm recommenderForm,
                                              @PathVariable Integer id) {
        Recommender recommender = recommenderForm.build();
        recommender.setId(id);
        recommender = recommenderService.update(recommender);
        return recommenderModelAssembler.toModel(new RecommenderDto(recommender));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        recommenderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
