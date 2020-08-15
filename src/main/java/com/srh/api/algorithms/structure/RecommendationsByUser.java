package com.srh.api.algorithms.structure;

import com.srh.api.model.Evaluator;
import com.srh.api.model.Recommendation;
import lombok.Data;

import java.util.List;

@Data
public class RecommendationsByUser {
    private Evaluator evaluator;
    private List<Recommendation> recommendations;
}
