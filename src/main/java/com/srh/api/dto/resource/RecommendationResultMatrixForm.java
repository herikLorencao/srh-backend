package com.srh.api.dto.resource;

import com.srh.api.builder.AlgorithmBuilder;
import com.srh.api.builder.RecommendationResultMatrixBuilder;
import com.srh.api.model.Algorithm;
import com.srh.api.model.RecommendationResultMatrix;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationResultMatrixForm {
    @NotNull
    private Integer algorithmId;

    @NotNull
    private Double passingScore;

    @NotNull
    private Boolean offline;

    public RecommendationResultMatrix build() {
        Algorithm algorithm = AlgorithmBuilder.anAlgorithm()
                .withId(algorithmId)
                .build();

        return RecommendationResultMatrixBuilder.aRecommendationResultMatrix()
                .withAlgorithm(algorithm)
                .build();
    }
}