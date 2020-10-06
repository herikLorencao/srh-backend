package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.resources.RecommendationAlgorithm;
import com.srh.api.dto.resource.RecommendationForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Collaborative implements RecommendationAlgorithm {
    @Override
    public List<?> calc(RecommendationForm form) {
        return null;
    }
}
