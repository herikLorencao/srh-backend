package com.srh.api.dto.resource;

import com.srh.api.builder.ItemBuilder;
import com.srh.api.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemForm {
    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String description;

    @NotNull
    @NotEmpty
    private String attributes;

    public Item build() {
        return ItemBuilder.anItem()
                .withName(name)
                .withDescription(description)
                .build();
    }
}
