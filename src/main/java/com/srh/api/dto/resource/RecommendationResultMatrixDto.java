package com.srh.api.dto.resource;

import com.srh.api.algorithms.structure.RecommendationMatrix;
import com.srh.api.model.Algorithm;
import com.srh.api.model.Recommendation;
import com.srh.api.model.RecommendationResultMatrix;
import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Getter
@Relation(value = "results")
public class RecommendationResultMatrixDto {
    private Integer id;
    private Double passingScore;
    private Boolean offline;
    private RecommendationMatrix result;

    @JsonIgnore
    private Algorithm algorithm;

    @JsonIgnore
    private List<Recommendation> recommendations;

    public RecommendationResultMatrixDto(RecommendationResultMatrix recommendationResultMatrix) {
        this.id = recommendationResultMatrix.getId();
        this.passingScore = recommendationResultMatrix.getPassingScore();
        this.offline = recommendationResultMatrix.getOffline();
        this.result = recommendationResultMatrix.getResult();
        this.algorithm = recommendationResultMatrix.getAlgorithm();
        this.recommendations = recommendationResultMatrix.getRecommendation();
    }
}
