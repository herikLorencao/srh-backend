package com.srh.api.controller;

import com.srh.api.dto.resource.EvaluatorDto;
import com.srh.api.dto.resource.EvaluatorForm;
import com.srh.api.hypermedia.RecommenderModelAssembler;
import com.srh.api.model.Evaluator;
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

import static com.srh.api.dto.resource.EvaluatorDto.convert;

@RestController
@RequestMapping("/users/recommenders")
public class EvaluatorController {
    @Autowired
    private RecommenderService recommenderService;

    @Autowired
    private RecommenderModelAssembler recommenderModelAssembler;

    @Autowired
    private PagedResourcesAssembler<EvaluatorDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<EvaluatorDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                                          Pageable pageInfo) {
        Page<Evaluator> users = recommenderService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(users));
    }

    @GetMapping("/{id}")
    public EntityModel<EvaluatorDto> find(@PathVariable Integer id) {
        Evaluator evaluator = recommenderService.find(id);
        return recommenderModelAssembler.toModel(new EvaluatorDto(evaluator));
    }

    @PostMapping
    public ResponseEntity<EntityModel<EvaluatorDto>> create(@RequestBody @Valid EvaluatorForm evaluatorForm,
                                                            UriComponentsBuilder uriBuilder) {
        Evaluator evaluator = evaluatorForm.build();
        recommenderService.save(evaluator);
        URI uri = uriBuilder.path("/users/recommenders/{id}").buildAndExpand(evaluator.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(recommenderModelAssembler.toModel(new EvaluatorDto(evaluator)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<EvaluatorDto> update(@RequestBody @Valid EvaluatorForm evaluatorForm,
                                            @PathVariable Integer id) {
        Evaluator evaluator = evaluatorForm.build();
        evaluator.setId(id);
        evaluator = recommenderService.update(evaluator);
        return recommenderModelAssembler.toModel(new EvaluatorDto(evaluator));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        recommenderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
