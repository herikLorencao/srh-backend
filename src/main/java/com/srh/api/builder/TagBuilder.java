package com.srh.api.builder;

import com.srh.api.model.ItemTag;
import com.srh.api.model.Tag;

import java.util.List;

public final class TagBuilder {
    protected Integer id;
    private String name;
    private List<ItemTag> itensTag;

    private TagBuilder() {
    }

    public static TagBuilder aTag() {
        return new TagBuilder();
    }

    public TagBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TagBuilder withItensTag(List<ItemTag> itensTag) {
        this.itensTag = itensTag;
        return this;
    }

    public TagBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public Tag build() {
        Tag tag = new Tag();
        tag.setName(name);
        tag.setItensTag(itensTag);
        tag.setId(id);
        return tag;
    }
}
