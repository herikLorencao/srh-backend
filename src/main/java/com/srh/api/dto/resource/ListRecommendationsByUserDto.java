package com.srh.api.dto.resource;

import com.srh.api.algorithms.structure.ListRecommendationsByUser;
import lombok.Getter;

import java.util.List;

@Getter
public class ListRecommendationsByUserDto {
    private String evaluator;
    private List<RecommendationResultDto> recommendations;

    public ListRecommendationsByUserDto(ListRecommendationsByUser listRecommendationsByUser) {
        this.evaluator = listRecommendationsByUser.getEvaluator().getName();
        this.recommendations =  RecommendationResultDto.convert(listRecommendationsByUser.getRecommendations());
    }
}
