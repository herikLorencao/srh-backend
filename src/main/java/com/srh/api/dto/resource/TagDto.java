package com.srh.api.dto.resource;

import com.srh.api.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "tags")
public class TagDto {
    private final Integer id;
    private final String name;

    public TagDto(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }

    public static Page<TagDto> convert(Page<Tag> tags) {
        return tags.map(TagDto::new);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
