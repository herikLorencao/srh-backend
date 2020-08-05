package com.srh.api.algorithms;

import com.srh.api.algorithms.structure.ListRecommendationsByUser;
import com.srh.api.model.Evaluator;

import java.util.List;

public interface AlgorithmCalc {
    public List<ListRecommendationsByUser> calc(
            Evaluator evaluator, Double passingScore, Boolean offline);
}
