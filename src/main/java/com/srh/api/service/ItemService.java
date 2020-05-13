package com.srh.api.service;

import com.srh.api.model.Item;
import com.srh.api.repository.ItemRepository;
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

        if (item.isPresent())
            return item.get();

        throw new ObjectNotFoundException(id, Item.class.getName());
    }

    public Page<Item> findAll(Pageable pageInfo) {
        return itemRepository.findAll(pageInfo);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Item update(Item item) {
        find(item.getId());
        return itemRepository.save(item);
    }

    public void delete(Integer id) {
        find(id);
        itemRepository.deleteById(id);
    }
}
