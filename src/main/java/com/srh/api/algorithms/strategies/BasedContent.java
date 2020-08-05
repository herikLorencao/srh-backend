package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.AlgorithmCalc;
import com.srh.api.algorithms.structure.ListRecommendationsByUser;
import com.srh.api.model.Evaluator;

import java.util.List;

public class BasedContent implements AlgorithmCalc {
    @Override
    public List<ListRecommendationsByUser> calc(
            Evaluator evaluator, Double passingScore, Boolean offline) {
        return null;
    }
}
