package com.srh.api.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRecommenderForm {
    @NotNull
    private Integer projectId;
    @NotNull
    private Integer recommenderId;
}
