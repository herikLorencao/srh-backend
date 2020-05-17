package com.srh.api.dto.resource;

import com.srh.api.builder.ProjectBuilder;
import com.srh.api.model.Project;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ProjectForm {

    @NotEmpty
    @NotNull
    @Length(min = 3)
    private String name;
    @NotEmpty
    @NotNull
    @Length(min = 3)
    private String description;

    public ProjectForm() {
    }

    public ProjectForm(@NotEmpty @NotNull @Length(min = 3) String name, @NotEmpty @NotNull @Length(min = 3) String description) {
        this.name = name;
        this.description = description;
    }

    public Project build() {
        return ProjectBuilder.aProject()
                .withName(name)
                .withDescription(description)
                .withDate(LocalDate.now())
                .build();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
