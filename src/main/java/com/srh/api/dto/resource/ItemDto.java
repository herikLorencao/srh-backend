package com.srh.api.dto.resource;

import com.srh.api.model.Item;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "itens")
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
}
