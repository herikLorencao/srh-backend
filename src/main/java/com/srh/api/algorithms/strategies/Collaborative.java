package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.resources.RecommendationAlgorithm;
import com.srh.api.algorithms.resources.collaborative.PrimaryMatrix;
import com.srh.api.algorithms.resources.collaborative.RecommendationMatrixEvaluator;
import com.srh.api.algorithms.resources.collaborative.SimilarityMatrix;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.Evaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Collaborative implements RecommendationAlgorithm {
    @Autowired
    PrimaryMatrix primaryMatrix;

    List<RecommendationMatrixEvaluator> recommendationMatrixEvaluatorList = new ArrayList<>();

    @Override
    public Object calc(RecommendationForm form) {
        primaryMatrix.build(form.getProjectId());

        for(Evaluator evaluator: primaryMatrix.getEvaluators()) {
            SimilarityMatrix similarityMatrix = new SimilarityMatrix(primaryMatrix, evaluator);
            RecommendationMatrixEvaluator recommendationMatrixEvaluator = new RecommendationMatrixEvaluator(
                    similarityMatrix, evaluator);
            return recommendationMatrixEvaluator.getSimilarityItemMatrix();
        }

        return recommendationMatrixEvaluatorList;
    }
}
