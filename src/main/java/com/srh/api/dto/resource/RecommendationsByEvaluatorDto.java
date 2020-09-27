package com.srh.api.dto.resource;

import com.srh.api.algorithms.resources.RecommendationsByEvaluator;
import com.srh.api.model.Recommendation;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Getter
@Relation(collectionRelation = "recommendations")
public class RecommendationsByEvaluatorDto {
    private final Integer evaluatorId;
    private final List<Recommendation> recommendations;

    public RecommendationsByEvaluatorDto(RecommendationsByEvaluator recommendationsByEvaluator) {
        this.evaluatorId = recommendationsByEvaluator.getEvaluator().getId();
        this.recommendations = recommendationsByEvaluator.getRecommendations();
    }

    public static Page<RecommendationsByEvaluatorDto> convert(
            Page<RecommendationsByEvaluator> recommendationsByEvaluators) {
        return recommendationsByEvaluators.map(RecommendationsByEvaluatorDto::new);
    }
}
