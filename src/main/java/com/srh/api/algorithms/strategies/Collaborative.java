package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.resources.RecommendationAlgorithm;
import com.srh.api.algorithms.resources.matrices.collaborative.PrimaryMatrix;
import com.srh.api.algorithms.resources.matrices.collaborative.SimilarityMatrix;
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

    List<SimilarityMatrix> similaritiesMatrix = new ArrayList<>();

    @Override
    public Object calc(RecommendationForm form) {
        primaryMatrix.build(form.getProjectId());

        for(Evaluator evaluator: primaryMatrix.getEvaluators()) {
            SimilarityMatrix similarityMatrix = new SimilarityMatrix(primaryMatrix, evaluator);
            similaritiesMatrix.add(similarityMatrix);
        }

        return similaritiesMatrix;
    }
}
