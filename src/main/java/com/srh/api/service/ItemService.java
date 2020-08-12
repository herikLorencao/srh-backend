package com.srh.api.service;

import com.srh.api.error.exception.ChangeRootRelationException;
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

    @Autowired
    private ProjectService projectService;

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

    @SneakyThrows
    public Item save(Item item) {
        if (!itemProjectIsOpenAndVisible(item)) {
            throw new ProjectNotOpenedException("The project is closed or invisible");
        }

        return itemRepository.save(item);
    }

    @SneakyThrows
    public Item update(Item item) {
        Item oldItem = find(item.getId());
        Integer oldProjectId = oldItem.getProject().getId();

        if (oldProjectId != item.getId()) {
            throw new ChangeRootRelationException("Não é possível alterar o projeto do item");
        }

        if (!itemProjectIsOpenAndVisible(item)) {
            throw new ProjectNotOpenedException("The project is closed or invisible");
        }

        item.setItemRatings(oldItem.getItemRatings());
        item.setRecommendations(oldItem.getRecommendations());

        return itemRepository.save(item);
    }

    public void delete(Integer id) {
        find(id);
        itemRepository.deleteById(id);
    }

    private boolean itemProjectIsOpenAndVisible(Item item) {
        Project project = projectService.find(item.getProject().getId());

        if (!project.getVisible())
            return false;

        return !project.getSituation()
                .equals(Situations.CLOSED);
    }
}
