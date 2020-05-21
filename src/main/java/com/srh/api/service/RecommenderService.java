package com.srh.api.service;

import com.srh.api.model.Recommender;
import com.srh.api.repository.RecommenderRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecommenderService {
    @Autowired
    private RecommenderRepository recommenderRepository;

    public Recommender find(Integer id) {
        Optional<Recommender> userRecommendation = recommenderRepository.findById(id);

        if (userRecommendation.isPresent())
            return userRecommendation.get();

        throw new ObjectNotFoundException(id, Recommender.class.getName());
    }

    public Page<Recommender> findAll(Pageable pageInfo) {
        return recommenderRepository.findAll(pageInfo);
    }

    public Recommender save(Recommender recommender) {
        return recommenderRepository.save(recommender);
    }

    public Recommender update(Recommender recommender) {
        find(recommender.getId());
        return recommenderRepository.save(recommender);
    }

    public void delete(Integer id) {
        find(id);
        recommenderRepository.deleteById(id);
    }
}
