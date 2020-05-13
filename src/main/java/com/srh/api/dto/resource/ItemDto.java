package com.srh.api.dto.resource;

import com.srh.api.model.Item;
import org.springframework.data.domain.Page;

import java.util.Objects;

public class ItemDto {
    private final Integer id;
    private final String name;
    private final String description;

    public ItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
    }

    public static Page<ItemDto> convert(Page<Item> items) {
        return items.map(ItemDto::new);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return Objects.equals(id, itemDto.id) &&
                Objects.equals(name, itemDto.name) &&
                Objects.equals(description, itemDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
