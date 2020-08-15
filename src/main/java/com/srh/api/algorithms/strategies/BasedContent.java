package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.AlgorithmCalc;
import com.srh.api.algorithms.structure.RecommendationsByUser;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.Evaluator;

import java.util.List;

public class BasedContent implements AlgorithmCalc {
    @Override
    public List<RecommendationsByUser> calc(RecommendationForm recommendationForm) {
        return null;
    }
}
