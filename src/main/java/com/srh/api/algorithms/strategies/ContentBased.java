package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.resources.*;
import com.srh.api.dto.resource.RecommendationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentBased implements RecommendationAlgorithm {
    @Autowired
    private BasicBaseMatrix primaryMatrix;

    @Autowired
    private RecommendationUtils recommendationUtils;

    @Autowired
    private RecommendationMatrix recommendationMatrix;

    private final List<RecommendationsByEvaluator> recommendationsByEvaluators = new ArrayList<>();

    @Override
//    public List<RecommendationsByEvaluator> calc(RecommendationForm form) {
    public Object calc(RecommendationForm form) {
//        recommendationUtils.configureRecommendationAlgorithm(form);
//        primaryMatrix = (BasicBaseMatrix) recommendationUtils.getPrimaryMatrix();



        return 2;
    }
}
