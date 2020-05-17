package com.srh.api.dto.resource;

import com.srh.api.builder.ItemBuilder;
import com.srh.api.model.Item;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ItemForm {
    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String name;
    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String description;

    public ItemForm() {
    }

    public ItemForm(@NotNull @NotEmpty @Length(min = 3) String name, @NotNull @NotEmpty @Length(min = 3)
            String description) {
        this.name = name;
        this.description = description;
    }

    public Item build() {
        return ItemBuilder.anItem()
                .withName(name)
                .withDescription(description)
                .build();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
