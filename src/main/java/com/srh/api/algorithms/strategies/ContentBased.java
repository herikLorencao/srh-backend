package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.resources.*;
import com.srh.api.algorithms.resources.basedcontent.SimilarityEvaluatorContent;
import com.srh.api.algorithms.resources.basedcontent.EvaluatorProfileMatrix;
import com.srh.api.algorithms.resources.basedcontent.ItemTagMatrix;
import com.srh.api.algorithms.resources.basedcontent.SimilarityEvaluatorProfile;
import com.srh.api.builder.AlgorithmBuilder;
import com.srh.api.builder.RecommendationBuilder;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.Algorithm;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContentBased implements RecommendationAlgorithm {
    @Autowired
    private BasicBaseMatrix primaryMatrix;

    @Autowired
    private RecommendationUtils recommendationUtils;

    @Autowired
    private RecommendationMatrix recommendationMatrix;

    @Autowired
    private ItemTagMatrix itemTagMatrix;

    private final List<RecommendationsByEvaluator> recommendationsByEvaluators = new ArrayList<>();
    private Double passingScore;
    private Integer decimalPrecision;

    @Override
    public List<RecommendationsByEvaluator> calc(RecommendationForm form) {
        passingScore = form.getPassingScore();
        decimalPrecision = form.getDecimalPrecision();

        buildBasicMatrix(form.getProjectId());
        itemTagMatrix.build(primaryMatrix.getItems());

        for(Evaluator evaluator: primaryMatrix.getEvaluators()) {
            EvaluatorProfileMatrix evaluatorProfileMatrix = mountEvaluatorProfile(evaluator);
            RecommendationsByEvaluator recommendationsByEvaluator = calculateRecommendationByEvaluator(
                    evaluatorProfileMatrix, itemTagMatrix, evaluator);
            recommendationsByEvaluators.add(recommendationsByEvaluator);
        }

        return recommendationsByEvaluators;
    }

    private void buildBasicMatrix(Integer projectId) {
        primaryMatrix.build(projectId);
    }

    private EvaluatorProfileMatrix mountEvaluatorProfile(Evaluator evaluator) {
        EvaluatorProfileMatrix evaluatorProfileMatrix = new EvaluatorProfileMatrix();
        evaluatorProfileMatrix.build(evaluator, primaryMatrix, itemTagMatrix);
        return evaluatorProfileMatrix;
    }

    private RecommendationsByEvaluator calculateRecommendationByEvaluator(EvaluatorProfileMatrix evaluatorProfileMatrix, ItemTagMatrix itemTagMatrix, Evaluator evaluator) {
        SimilarityEvaluatorProfile similarityEvaluatorProfile = new SimilarityEvaluatorProfile(evaluatorProfileMatrix);
        SimilarityEvaluatorContent similarityEvaluatorContent = new SimilarityEvaluatorContent(
                similarityEvaluatorProfile.getContent(),
                itemTagMatrix.getContent(),
                primaryMatrix.getItems(),
                primaryMatrix.getTags()
        );

        return getRecommendations(evaluator, similarityEvaluatorContent);
    }

    private RecommendationsByEvaluator getRecommendations(Evaluator evaluator, SimilarityEvaluatorContent similarityEvaluatorContent) {
        RecommendationsByEvaluator recommendationsByEvaluator = new RecommendationsByEvaluator();
        recommendationsByEvaluator.setEvaluator(evaluator);
        List<Recommendation> recommendations = new ArrayList<>();
        Integer evaluatorRow = primaryMatrix.getEvaluators().indexOf(evaluator);

        for(int j = 0; j < primaryMatrix.getColSize(); j++) {
            if (primaryMatrix.getContent()[evaluatorRow][j] == null) {
                Double recommendationScore = roundValue(similarityEvaluatorContent.getRecommendationForItemIdx(j), decimalPrecision);
                recommendations.add(buildRecommendation(recommendationScore, evaluator, evaluatorRow, similarityEvaluatorContent));
            }
        }

        recommendationsByEvaluator.setRecommendations(recommendations);

        return recommendationsByEvaluator;
    }

    private Recommendation buildRecommendation(Double score, Evaluator evaluator, Integer itemColumnIdx,
        SimilarityEvaluatorContent similarityEvaluatorContent) {
        Algorithm algorithm = AlgorithmBuilder.anAlgorithm()
                .withId(2)
                .build();

        return RecommendationBuilder.aRecommendation()
                .withId(1)
                .withAlgorithm(algorithm)
                .withRuntimeInSeconds(1)
                .withWeight(score)
                .withEvaluator(evaluator)
                .withMatrixId(1)
                .withDate(LocalDateTime.now())
                .withItem(similarityEvaluatorContent.getItemByIdx(itemColumnIdx))
                .build();
    }

    private Double roundValue(Double value, Integer decimalPrecision) {
        return BigDecimal.valueOf(value)
                .setScale(decimalPrecision, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
