package com.srh.api.controller;

import com.srh.api.dto.resource.ProjectEvaluatorDto;
import com.srh.api.dto.resource.ProjectEvaluatorForm;
import com.srh.api.dto.resource.EvaluatorDto;
import com.srh.api.hypermedia.ProjectRecommenderModelAssembler;
import com.srh.api.hypermedia.RecommenderModelAssembler;
import com.srh.api.model.ProjectEvaluator;
import com.srh.api.model.Evaluator;
import com.srh.api.service.ProjectEvaluatorService;
import lombok.SneakyThrows;
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

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/projects/{projectId}/evaluators")
public class ProjectEvaluatorController {
    @Autowired
    private ProjectEvaluatorService projectEvaluatorService;

    @Autowired
    private ProjectRecommenderModelAssembler projectRecommenderModelAssembler;

    @Autowired
    private RecommenderModelAssembler recommenderModelAssembler;

    @Autowired
    private PagedResourcesAssembler<EvaluatorDto> recommenderDtoPagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<EvaluatorDto>> listRecommendersByProject(
            @PathVariable Integer projectId, @PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<Evaluator> recommendersPage = projectEvaluatorService.
                listEvaluatorsByProject(projectId, pageInfo);
        return recommenderDtoPagedResourcesAssembler.toModel(EvaluatorDto
                .convert(recommendersPage));
    }

    @GetMapping("/{recommenderId}")
    public EntityModel<EvaluatorDto> findRecommenderInProject(@PathVariable Integer projectId,
                                                              @PathVariable Integer recommenderId) {
        Evaluator evaluator = projectEvaluatorService.findEvaluatorByProject(projectId,
                recommenderId);
        return recommenderModelAssembler.toModel(new EvaluatorDto(evaluator));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<EntityModel<ProjectEvaluatorDto>> linkRecommendersToProject(
            @PathVariable Integer projectId, @RequestBody @Valid ProjectEvaluatorForm
            projectRecommenderForm, UriComponentsBuilder uriBuilder) {

        ProjectEvaluator projectEvaluator = projectEvaluatorService.save(
                projectRecommenderForm.getProjectId(),
                projectRecommenderForm.getEvaluatorId());

        URI uri = uriBuilder.path("/projects/{projectId}/recommenders/{recommenderId}")
                .buildAndExpand(projectId, projectEvaluator.getEvaluator().getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(projectRecommenderModelAssembler.toModel(
                        new ProjectEvaluatorDto(projectEvaluator)
                ));
    }

    @DeleteMapping("/{recommenderId}")
    @SneakyThrows
    public ResponseEntity<Void> delete(@PathVariable Integer projectId,
                                       @PathVariable Integer recommenderId) {
        projectEvaluatorService.delete(projectId, recommenderId);
        return ResponseEntity.noContent().build();
    }
}
