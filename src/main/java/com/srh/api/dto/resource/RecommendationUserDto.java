package com.srh.api.dto.resource;

import com.srh.api.algorithms.structure.RecommendationsByUser;
import com.srh.api.model.Recommendation;
import org.springframework.data.domain.Page;

import java.util.List;

public class RecommendationUserDto {
    private Integer userId;
    private List<Recommendation> recommendations;

    public RecommendationUserDto(RecommendationsByUser recommendationsByUser) {
        this.userId = recommendationsByUser.getEvaluator().getId();
        this.recommendations = recommendationsByUser.getRecommendations();
    }

    public static Page<RecommendationUserDto> convert(Page<RecommendationsByUser> recommendations) {
        return recommendations.map(RecommendationUserDto::new);
    }
}
