package com.srh.api.algorithms.resources;

import com.srh.api.algorithms.strategies.*;
import com.srh.api.error.exception.InvalidAlgorithmRecommendationException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlgorithmStrategyAsync {
    @Autowired
    private CollaborativeAsync collaborative;

    @Autowired
    private ContentBasedAsync contentBased;

    @SneakyThrows
    public RecommendationAlgorithm getAlgorithm(Integer algorithmId) {
        String algorithmValue = String.valueOf(algorithmId);

        switch (algorithmValue) {
            case "1":
                return collaborative;
            case "2":
                return contentBased;
            default:
                throw new InvalidAlgorithmRecommendationException("Invalid algorithm");
        }
    }
}
