package com.srh.api.service;

import com.srh.api.model.Item;
import com.srh.api.model.ItemRating;
import com.srh.api.model.Evaluator;
import com.srh.api.repository.ItemRatingRepository;
import lombok.Setter;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemRatingService {
    @Autowired
    private ItemRatingRepository itemRatingRepository;

    @Autowired
    private EvaluatorService evaluatorService;

    @Autowired
    private ItemService itemService;

    @Setter
    private String authorizationHeaderValue;

    public ItemRating find(Integer id) {
        Optional<ItemRating> itemRating = itemRatingRepository.findById(id);

        if (itemRating.isPresent())
            return itemRating.get();

        throw new ObjectNotFoundException(id, ItemRating.class.getName());
    }

    public Page<ItemRating> findAll(Pageable pageInfo) {
        return itemRatingRepository.findAll(pageInfo);
    }

    public ItemRating save(ItemRating itemRating) {
        Integer evaluatorId = itemRating.getEvaluator().getId();
        Integer itemId = itemRating.getItem().getId();

        Item item = itemService.find(itemId);
        Evaluator evaluator = evaluatorService.find(evaluatorId);

        itemRating.setItem(item);
        itemRating.setEvaluator(evaluator);

        return itemRatingRepository.save(itemRating);
    }

    public ItemRating update(ItemRating itemRating) {
        find(itemRating.getId());
        return itemRatingRepository.save(itemRating);
    }

    public void delete(Integer id) {
        find(id);
        itemRatingRepository.deleteById(id);
    }
}
