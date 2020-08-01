package com.srh.api.algorithms;

import com.srh.api.algorithms.strategies.*;
import com.srh.api.error.exception.InvalidAlgorithmRecommendationException;
import lombok.SneakyThrows;

public class AlgorithmStrategy {
    @SneakyThrows
    public static AlgorithmCalc findInstance(Integer algorithmId) {
        String idValue = String.valueOf(algorithmId);

        switch (idValue) {
            case "1":
                return new Collaborative();
            case "2":
                return new BasedContent();
            case "3":
                return new WeightedHybrid();
            case "4":
                return new WeightedHybridWithThreads();
            case "5":
                return new MixedHybrid();
            case "6":
                return new MixedHybridWithThreads();
            default:
                throw new InvalidAlgorithmRecommendationException("Algoritmo inválido");
        }
    }
}
