package com.srh.api.algorithms;

import com.srh.api.algorithms.structure.RecommendationsByUser;
import com.srh.api.dto.resource.RecommendationForm;

import java.util.List;

public interface AlgorithmCalc {
    public List<RecommendationsByUser> calc(RecommendationForm recommendationInfo);
}
