package com.srh.api.algorithms.resources;

import com.srh.api.algorithms.strategies.Collaborative;
import com.srh.api.error.exception.InvalidAlgorithmRecommendationException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlgorithmStrategy {
    @Autowired
    private Collaborative collaborative;

    @SneakyThrows
    public RecommendationAlgorithm getAlgorithm(Integer algorithmId) {
        String algorithmValue = String.valueOf(algorithmId);

        switch (algorithmValue) {
            case "1":
                return collaborative;
            default:
                throw new InvalidAlgorithmRecommendationException("Invalid algorithm");
        }
    }
}
