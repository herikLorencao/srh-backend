package com.srh.api.service;

import com.srh.api.model.*;
import com.srh.api.repository.ItemRepository;
import com.srh.api.repository.RecommendationRepository;
import com.srh.api.utils.PageUtil;
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

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private AlgorithmService algorithmService;

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

    public Page<Recommendation> listRecommendationsByAlgorithm(Integer algorithmId, Pageable pageInfo) {
        Algorithm algorithm = algorithmService.find(algorithmId);
        List<Recommendation> recommendations = recommendationRepository.findByAlgorithm(algorithm);
        PageUtil<Recommendation> pageUtil = new PageUtil<>(pageInfo, recommendations);
        return pageUtil.getPage();
    }

    public Page<Recommendation> listRecommendationsByMatrixId(Integer matrixId, Pageable pageInfo) {
        List<Recommendation> recommendations = recommendationRepository.findByMatrixId(matrixId);
        PageUtil<Recommendation> pageUtil = new PageUtil<>(pageInfo, recommendations);
        return pageUtil.getPage();
    }

    public Page<Recommendation> listRecommendationsByTag(Integer tagId, Pageable pageInfo) {
        Tag tag = tagService.find(tagId);
        List<Item> itens = itemRepository.findByTags(tag);
        List<Recommendation> recommendations = new ArrayList<>();

        for (Item item : itens) {
            recommendations.addAll(item.getRecommendations());
        }

        PageUtil<Recommendation> pageUtil = new PageUtil<>(pageInfo, recommendations);
        return pageUtil.getPage();
    }
}
