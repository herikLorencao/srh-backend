package com.srh.api.algorithms.resources;

import com.srh.api.algorithms.strategies.Collaborative;
import com.srh.api.algorithms.strategies.ContentBased;
import com.srh.api.algorithms.strategies.MixedHybrid;
import com.srh.api.algorithms.strategies.WeightedHybrid;
import com.srh.api.error.exception.InvalidAlgorithmRecommendationException;
import com.srh.api.model.TypeRecommendation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlgorithmStrategy {
    @Autowired
    private Collaborative collaborative;

    @Autowired
    private ContentBased contentBased;

    @Autowired
    private WeightedHybrid weightedHybrid;

    @Autowired
    private MixedHybrid mixedHybrid;

    @SneakyThrows
    public RecommendationAlgorithm getAlgorithm(Integer algorithmId) {
        String algorithmValue = String.valueOf(algorithmId);

        switch (algorithmValue) {
            case "1":
                return collaborative;
            case "2":
                return contentBased;
            case "3":
                return weightedHybrid;
            case "4":
                return mixedHybrid;
            default:
                throw new InvalidAlgorithmRecommendationException("Invalid algorithm");
        }
    }
}
