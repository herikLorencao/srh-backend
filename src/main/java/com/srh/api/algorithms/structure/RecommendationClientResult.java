package com.srh.api.algorithms.structure;

import lombok.Data;

import java.util.List;

@Data
public class RecommendationClientResult {
    private Integer matrixId;
    private List<ListRecommendationsByUser> recommendations;
}
