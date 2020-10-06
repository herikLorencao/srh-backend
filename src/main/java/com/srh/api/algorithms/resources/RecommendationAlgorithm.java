package com.srh.api.algorithms.resources;

import com.srh.api.dto.resource.RecommendationForm;

import java.util.List;

public interface RecommendationAlgorithm {
    public List<?> calc(RecommendationForm form);
}
