package com.srh.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class ItemTag {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemTag itemTag = (ItemTag) o;
        return Objects.equals(id, itemTag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
