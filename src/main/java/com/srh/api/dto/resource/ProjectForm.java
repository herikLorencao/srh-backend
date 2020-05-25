package com.srh.api.dto.resource;

import com.srh.api.builder.AdminBuilder;
import com.srh.api.builder.ProjectBuilder;
import com.srh.api.error.exception.InvalidSituationException;
import com.srh.api.model.Admin;
import com.srh.api.model.Project;
import com.srh.api.model.Situations;
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
    @NotNull
    private Integer adminId;
    @NotNull
    @NotEmpty
    private String situation;

    public ProjectForm() {
    }

    public ProjectForm(@NotEmpty @NotNull @Length(min = 3) String name, @NotEmpty @NotNull
    @Length(min = 3) String description, @NotNull Integer adminId, @NotNull @NotEmpty
                               String situation) {
        this.name = name;
        this.description = description;
        this.adminId = adminId;
        this.situation = situation;
    }

    public Project build() throws InvalidSituationException {
        Situations situation = getSituation();

        Admin admin = AdminBuilder.anAdmin()
                .withId(adminId)
                .build();

        return ProjectBuilder.aProject()
                .withName(name)
                .withDescription(description)
                .withAdmin(admin)
                .withSituation(situation)
                .withDate(LocalDate.now())
                .build();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public Situations getSituation() throws InvalidSituationException {
        switch (situation.toLowerCase()) {
            case "o":
                return Situations.OPEN;
            case "c":
                return Situations.CLOSED;
            default:
                throw new InvalidSituationException("Value in situation is not valid");
        }
    }
}
