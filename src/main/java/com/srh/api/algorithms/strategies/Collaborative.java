package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.resources.RecommendationAlgorithm;
import com.srh.api.algorithms.resources.matrices.collaborative.SimilarityMatrix;
import com.srh.api.dto.resource.RecommendationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Collaborative implements RecommendationAlgorithm {
    @Autowired
    SimilarityMatrix similarityMatrix;

    @Override
    public Object calc(RecommendationForm form) {
        similarityMatrix.fill(form.getProjectId());
        return similarityMatrix.generateSimilarity();
    }
}
