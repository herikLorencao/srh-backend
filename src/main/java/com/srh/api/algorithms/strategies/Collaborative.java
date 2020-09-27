package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.resources.RecommendationAlgorithm;
import com.srh.api.algorithms.resources.RecommendationsByEvaluator;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.service.EvaluatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Collaborative implements RecommendationAlgorithm {
    @Autowired
    private EvaluatorService evaluatorService;

    @Override
    public List<RecommendationsByEvaluator> calc(RecommendationForm form) {
        List<RecommendationsByEvaluator> recommendationsByEvaluators = new ArrayList<>();



        return recommendationsByEvaluators;
    }
}
