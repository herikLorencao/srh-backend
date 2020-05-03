package com.srh.api.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemTagPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemTagPK itemTagPK = (ItemTagPK) o;
        return Objects.equals(item, itemTagPK.item) &&
                Objects.equals(tag, itemTagPK.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, tag);
    }
}