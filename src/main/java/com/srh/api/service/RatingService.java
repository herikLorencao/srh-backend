package com.srh.api.service;

import com.srh.api.model.Item;
import com.srh.api.model.Rating;
import com.srh.api.model.Recommender;
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

    public Rating find(Integer id) {
        Optional<Rating> rating = ratingRepository.findById(id);

        if (rating.isPresent())
            return rating.get();

        throw new ObjectNotFoundException(id, Rating.class.getName());
    }

    public Page<Rating> findAll(Pageable pageInfo) {
        return ratingRepository.findAll(pageInfo);
    }

    public Rating save(Rating rating) {
        Integer recommenderId = rating.getUser().getId();
        Integer itemId = rating.getItem().getId();

        Item item = itemService.find(itemId);
        Recommender recommender = recommenderService.find(recommenderId);

        rating.setItem(item);
        rating.setUser(recommender);

        return ratingRepository.save(rating);
    }

    public Rating update(Rating rating) {
        find(rating.getId());
        return ratingRepository.save(rating);
    }

    public void delete(Integer id) {
        find(id);
        ratingRepository.deleteById(id);
    }

}
