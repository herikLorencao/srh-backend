package com.srh.api.dto.resource;

import com.srh.api.builder.ItemBuilder;
import com.srh.api.model.Item;

import java.util.Objects;

public class ItemForm {
    private final String name;
    private final String description;

    public ItemForm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemForm itemForm = (ItemForm) o;
        return Objects.equals(name, itemForm.name) &&
                Objects.equals(description, itemForm.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    public Item build() {
        return ItemBuilder.anItem()
                .withName(name)
                .withDescription(description)
                .build();
    }
}
