package com.srh.api.algorithms.resources;

import com.srh.api.algorithms.math.CellPosition;
import com.srh.api.algorithms.resources.utils.RecommendationsByEvaluator;
import com.srh.api.dto.resource.RecommendationForm;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

public interface RecommendationAlgorithmAsync {
    public List<RecommendationsByEvaluator> calc(RecommendationForm form);
    public List<CellPosition> getRecommendationsPositions();
}
