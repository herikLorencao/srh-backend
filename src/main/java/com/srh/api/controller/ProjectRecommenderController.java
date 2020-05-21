package com.srh.api.controller;

import com.srh.api.dto.resource.ProjectRecommenderDto;
import com.srh.api.dto.resource.ProjectRecommenderForm;
import com.srh.api.dto.resource.RecommenderDto;
import com.srh.api.error.exception.DuplicateValueException;
import com.srh.api.hypermedia.ProjectRecommenderModelAssembler;
import com.srh.api.hypermedia.RecommenderModelAssembler;
import com.srh.api.model.ProjectRecommender;
import com.srh.api.model.Recommender;
import com.srh.api.service.ProjectRecommenderService;
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
public class ProjectRecommenderController {
    @Autowired
    private ProjectRecommenderService projectRecommenderService;

    @Autowired
    private ProjectRecommenderModelAssembler projectRecommenderModelAssembler;

    @Autowired
    private RecommenderModelAssembler recommenderModelAssembler;

    @Autowired
    private PagedResourcesAssembler<RecommenderDto> recommenderDtoPagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<RecommenderDto>> listRecommendersByProject(
            @PathVariable Integer projectId, @PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<Recommender> recommendersPage = projectRecommenderService.
                listRecommendersByProject(projectId, pageInfo);
        return recommenderDtoPagedResourcesAssembler.toModel(RecommenderDto
                .convert(recommendersPage));
    }

    @PostMapping
    public ResponseEntity<EntityModel<ProjectRecommenderDto>> linkRecommendersToProject(
            @PathVariable Integer projectId, @RequestBody @Valid ProjectRecommenderForm
            projectRecommenderForm, UriComponentsBuilder uriBuilder) throws DuplicateValueException {

        ProjectRecommender projectRecommender = projectRecommenderService.save(
                projectRecommenderForm.getProjectId(),
                projectRecommenderForm.getRecommenderId());

        URI uri = uriBuilder.path("/projects/{projectId}/recommenders/{recommenderId}")
                .buildAndExpand(projectId, projectRecommender.getRecommender().getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(projectRecommenderModelAssembler.toModel(
                        new ProjectRecommenderDto(projectRecommender)
                ));
    }
}
