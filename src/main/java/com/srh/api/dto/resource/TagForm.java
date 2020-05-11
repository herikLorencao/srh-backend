package com.srh.api.dto.resource;

import com.srh.api.builder.TagBuilder;
import com.srh.api.model.Tag;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TagForm {
    @NotEmpty
    @NotNull
    private String name;

    public TagForm() {
    }

    public TagForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Tag build() {
        return TagBuilder.aTag()
                .withName(name)
                .build();
    }
}
