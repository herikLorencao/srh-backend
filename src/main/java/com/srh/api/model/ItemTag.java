package com.srh.api.model;

import com.srh.api.error.exception.DuplicateValueException;
import com.srh.api.error.exception.RelationshipNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.List;

@Data
@AllArgsConstructor
public class ItemTag {
    private Item item;
    private Tag tag;

    @SneakyThrows
    public void addEntities() {
        addItemInTag();
        addTagInItem();
    }

    @SneakyThrows
    public void removeEntities() {
        removeItemInTag();
        removeTagInItem();
    }

    @SneakyThrows
    private void addItemInTag() {
        List<Item> itensInTag = getItensListInTag();

        if (itensInTag.contains(item))
            throw new DuplicateValueException("O item já possui vínculo com a tag");

        itensInTag.add(item);
    }

    @SneakyThrows
    private void addTagInItem() {
        List<Tag> tagsInItem = getTagListInItem();

        if (tagsInItem.contains(tag))
            throw new DuplicateValueException("A tag já possui vínculo com o item");

        tagsInItem.add(tag);
    }

    @SneakyThrows
    private void removeItemInTag() {
        List<Item> itensInTag = getItensListInTag();

        if (!itensInTag.contains(item))
            throw new RelationshipNotFoundException("Não existe vínculo entre o Item e a Tag");

        itensInTag.remove(item);
    }

    @SneakyThrows
    private void removeTagInItem() {
        List<Tag> tagsInItem = getTagListInItem();

        if (!tagsInItem.contains(tag))
            throw new RelationshipNotFoundException("Não existe vínculo entre a Tag e o Item");

        tagsInItem.remove(tag);
    }

    private List<Item> getItensListInTag() {
        return tag.getItens();
    }

    private List<Tag> getTagListInItem() {
        return item.getTags();
    }
}
