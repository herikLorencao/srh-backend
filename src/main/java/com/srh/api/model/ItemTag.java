package com.srh.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemTag {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @EmbeddedId
    private ItemTagPK id = new ItemTagPK();

    public ItemTag() {
    }

    public ItemTag(Item item, Tag tag) {
        this.id.setItem(item);
        this.id.setTag(tag);
    }

    @JsonIgnore
    public Item getItem() {
        return this.id.getItem();
    }

    public void setItem(Item item) {
        this.setItem(item);
    }

    public Tag getTag() {
        return this.id.getTag();
    }

    public void setTag(Tag tag) {
        this.id.setTag(tag);
    }

    public ItemTagPK getId() {
        return id;
    }

    public void setId(ItemTagPK id) {
        this.id = id;
    }
}
