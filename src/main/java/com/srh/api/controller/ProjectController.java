package com.srh.api.controller;

import com.srh.api.dto.resource.ProjectDto;
import com.srh.api.dto.resource.ProjectForm;
import com.srh.api.error.exception.InvalidSituationException;
import com.srh.api.hypermedia.ProjectModelAssembler;
import com.srh.api.model.Project;
import com.srh.api.service.ProjectService;
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

import static com.srh.api.dto.resource.ProjectDto.convert;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectModelAssembler projectModelAssembler;

    @Autowired
    private PagedResourcesAssembler<ProjectDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<ProjectDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                               Pageable pageInfo) {
        Page<Project> projects = projectService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(projects));
    }

    @GetMapping("/{id}")
    public EntityModel<ProjectDto> find(@PathVariable Integer id) {
        Project project = projectService.find(id);
        return projectModelAssembler.toModel(new ProjectDto(project));
    }

    @PostMapping
    public ResponseEntity<EntityModel<ProjectDto>> create(@RequestBody @Valid ProjectForm projectForm,
                                                          UriComponentsBuilder uriBuilder) throws InvalidSituationException {
        Project project = projectForm.build();
        Project projectPersist = projectService.save(project);
        URI uri = uriBuilder.path("/projects/{id}").buildAndExpand(project.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(projectModelAssembler.toModel(new ProjectDto(projectPersist)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<ProjectDto> update(@RequestBody @Valid ProjectForm projectForm,
                                          @PathVariable Integer id) throws InvalidSituationException {
        Project project = projectForm.build();
        project.setId(id);
        project = projectService.update(project);
        return projectModelAssembler.toModel(new ProjectDto(project));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
