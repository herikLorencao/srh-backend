package com.srh.api.dto.resource;

import com.srh.api.model.Project;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class ProjectDto {
    private final Integer id;
    private final String name;
    private final String description;
    private final LocalDate date;

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.date = project.getDate();
    }

    public static Page<ProjectDto> convert(Page<Project> projects) {
        return projects.map(ProjectDto::new);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
}
