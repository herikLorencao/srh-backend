package com.srh.api.service;

import com.srh.api.model.Item;
import com.srh.api.model.ItemRating;
import com.srh.api.model.Evaluator;
import com.srh.api.repository.RatingRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RecommenderService recommenderService;

    @Autowired
    private ItemService itemService;

    public ItemRating find(Integer id) {
        Optional<ItemRating> rating = ratingRepository.findById(id);

        if (rating.isPresent())
            return rating.get();

        throw new ObjectNotFoundException(id, ItemRating.class.getName());
    }

    public Page<ItemRating> findAll(Pageable pageInfo) {
        return ratingRepository.findAll(pageInfo);
    }

    public ItemRating save(ItemRating itemRating) {
        Integer recommenderId = itemRating.getUser().getId();
        Integer itemId = itemRating.getItem().getId();

        Item item = itemService.find(itemId);
        Evaluator evaluator = recommenderService.find(recommenderId);

        itemRating.setItem(item);
        itemRating.setUser(evaluator);

        return ratingRepository.save(itemRating);
    }

    public ItemRating update(ItemRating itemRating) {
        find(itemRating.getId());
        return ratingRepository.save(itemRating);
    }

    public void delete(Integer id) {
        find(id);
        ratingRepository.deleteById(id);
    }

}
