package com.srh.api.controller;

import com.srh.api.dto.resource.ProjectEvaluatorDto;
import com.srh.api.dto.resource.ProjectEvaluatorForm;
import com.srh.api.dto.resource.EvaluatorDto;
import com.srh.api.hypermedia.ProjectRecommenderModelAssembler;
import com.srh.api.hypermedia.RecommenderModelAssembler;
import com.srh.api.model.ProjectRecommender;
import com.srh.api.model.Evaluator;
import com.srh.api.service.ProjectRecommenderService;
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
@RequestMapping("/projects/{projectId}/recommenders")
public class ProjectEvaluatorController {
    @Autowired
    private ProjectRecommenderService projectRecommenderService;

    @Autowired
    private ProjectRecommenderModelAssembler projectRecommenderModelAssembler;

    @Autowired
    private RecommenderModelAssembler recommenderModelAssembler;

    @Autowired
    private PagedResourcesAssembler<EvaluatorDto> recommenderDtoPagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<EvaluatorDto>> listRecommendersByProject(
            @PathVariable Integer projectId, @PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<Evaluator> recommendersPage = projectRecommenderService.
                listRecommendersByProject(projectId, pageInfo);
        return recommenderDtoPagedResourcesAssembler.toModel(EvaluatorDto
                .convert(recommendersPage));
    }

    @GetMapping("/{recommenderId}")
    public EntityModel<EvaluatorDto> findRecommenderInProject(@PathVariable Integer projectId,
                                                              @PathVariable Integer recommenderId) {
        Evaluator evaluator = projectRecommenderService.findRecommenderByProject(projectId,
                recommenderId);
        return recommenderModelAssembler.toModel(new EvaluatorDto(evaluator));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<EntityModel<ProjectEvaluatorDto>> linkRecommendersToProject(
            @PathVariable Integer projectId, @RequestBody @Valid ProjectEvaluatorForm
            projectRecommenderForm, UriComponentsBuilder uriBuilder) {

        ProjectRecommender projectRecommender = projectRecommenderService.save(
                projectRecommenderForm.getProjectId(),
                projectRecommenderForm.getRecommenderId());

        URI uri = uriBuilder.path("/projects/{projectId}/recommenders/{recommenderId}")
                .buildAndExpand(projectId, projectRecommender.getEvaluator().getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(projectRecommenderModelAssembler.toModel(
                        new ProjectEvaluatorDto(projectRecommender)
                ));
    }

    @DeleteMapping("/{recommenderId}")
    @SneakyThrows
    public ResponseEntity<Void> delete(@PathVariable Integer projectId,
                                       @PathVariable Integer recommenderId) {
        projectRecommenderService.delete(projectId, recommenderId);
        return ResponseEntity.noContent().build();
    }
}
