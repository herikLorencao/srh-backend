package com.srh.api.dto.resource;

import com.srh.api.model.Recommendation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class RecommendationResultDto {
    private Integer id;
    private Double weight;

    public static List<RecommendationResultDto> convert(List<Recommendation> recommendations) {
        List<RecommendationResultDto> recommendationResultList = new ArrayList<>();

        for (Recommendation recommendation : recommendations) {
            RecommendationResultDto recommendationResultDto = new RecommendationResultDto(
                    recommendation.getId(),
                    recommendation.getWeight()
            );
            recommendationResultList.add(recommendationResultDto);
        }

        return recommendationResultList;
    }
}
