package com.srh.api.service;

import com.srh.api.error.exception.ProjectNotOpenedException;
import com.srh.api.model.Item;
import com.srh.api.model.Project;
import com.srh.api.model.Situations;
import com.srh.api.repository.ItemRepository;
import lombok.SneakyThrows;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item find(Integer id) {
        Optional<Item> item = itemRepository.findById(id);

        if (item.isPresent()) {
            return item.get();
        }

        throw new ObjectNotFoundException(id, Item.class.getName());
    }

    public Page<Item> findAll(Pageable pageInfo) {
        return itemRepository.findAll(pageInfo);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @SneakyThrows
    public Item update(Item item) {
        find(item.getId());

        if (!itemProjectIsOpenAndVisible(item))
            throw new ProjectNotOpenedException("O projeto está fechado ou com a visibilidade " +
                    "desativada");

        return itemRepository.save(item);
    }

    public void delete(Integer id) {
        find(id);
        itemRepository.deleteById(id);
    }

    private boolean itemProjectIsOpenAndVisible(Item item) {
        Project project = item.getProject();

        if (!project.getVisible())
            return false;

        return !project.getSituation()
                .equals(Situations.CLOSED);
    }
}
