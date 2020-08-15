package com.srh.api.service;

import com.srh.api.algorithms.AlgorithmCalc;
import com.srh.api.algorithms.AlgorithmStrategy;
import com.srh.api.algorithms.structure.RecommendationsByUser;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Recommendation;
import com.srh.api.repository.RecommendationRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationService {
    @Autowired
    private RecommendationRepository recommendationRepository;

    public Recommendation find(Integer id) {
        Optional<Recommendation> recommendation = recommendationRepository.findById(id);

        if (recommendation.isPresent())
            return recommendation.get();

        throw new ObjectNotFoundException(id, Recommendation.class.getName());
    }

    public Page<Recommendation> findAll(Pageable pageInfo) {
        return recommendationRepository.findAll(pageInfo);
    }

    public Recommendation save(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    public Recommendation update(Recommendation recommendation) {
        find(recommendation.getId());
        return recommendationRepository.save(recommendation);
    }

    public void delete(Integer id) {
        find(id);
        recommendationRepository.deleteById(id);
    }

    public List<RecommendationsByUser> generateRecommendation(RecommendationForm recommendationInfo) {
        AlgorithmCalc algorithm = AlgorithmStrategy.findInstance(recommendationInfo.getAlgorithmId());
        return algorithm.calc(recommendationInfo);
    }
}
