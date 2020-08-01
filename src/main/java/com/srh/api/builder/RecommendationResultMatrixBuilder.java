package com.srh.api.builder;

import com.srh.api.algorithms.structure.RecommendationMatrix;
import com.srh.api.model.Algorithm;
import com.srh.api.model.Recommendation;
import com.srh.api.model.RecommendationResultMatrix;

import java.util.List;

public final class RecommendationResultMatrixBuilder {
    private Integer id;
    private List<Recommendation> recommendation;
    private Algorithm algorithm;
    private RecommendationMatrix result;
    private Double passingScore;
    private Boolean offline;

    private RecommendationResultMatrixBuilder() {
    }

    public static RecommendationResultMatrixBuilder aRecommendationResultMatrix() {
        return new RecommendationResultMatrixBuilder();
    }

    public RecommendationResultMatrixBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public RecommendationResultMatrixBuilder withRecommendation(List<Recommendation> recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    public RecommendationResultMatrixBuilder withAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public RecommendationResultMatrixBuilder withResult(RecommendationMatrix result) {
        this.result = result;
        return this;
    }

    public RecommendationResultMatrixBuilder withPassingScore(Double passingScore) {
        this.passingScore = passingScore;
        return this;
    }

    public RecommendationResultMatrixBuilder withOffline(Boolean offline) {
        this.offline = offline;
        return this;
    }

    public RecommendationResultMatrix build() {
        RecommendationResultMatrix recommendationResultMatrix = new RecommendationResultMatrix();
        recommendationResultMatrix.setId(id);
        recommendationResultMatrix.setRecommendation(recommendation);
        recommendationResultMatrix.setAlgorithm(algorithm);
        recommendationResultMatrix.setResult(result);
        recommendationResultMatrix.setPassingScore(passingScore);
        recommendationResultMatrix.setOffline(offline);
        return recommendationResultMatrix;
    }
}
