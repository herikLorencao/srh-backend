package com.srh.api.service;

import com.srh.api.model.Evaluator;
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

    public Evaluator find(Integer id) {
        Optional<Evaluator> userRecommendation = recommenderRepository.findById(id);

        if (userRecommendation.isPresent())
            return userRecommendation.get();

        throw new ObjectNotFoundException(id, Evaluator.class.getName());
    }

    public Page<Evaluator> findAll(Pageable pageInfo) {
        return recommenderRepository.findAll(pageInfo);
    }

    public Evaluator save(Evaluator evaluator) {
        return recommenderRepository.save(evaluator);
    }

    public Evaluator update(Evaluator evaluator) {
        find(evaluator.getId());
        return recommenderRepository.save(evaluator);
    }

    public void delete(Integer id) {
        find(id);
        recommenderRepository.deleteById(id);
    }
}
