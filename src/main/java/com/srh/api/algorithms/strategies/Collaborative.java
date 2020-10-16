package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.math.CellPosition;
import com.srh.api.algorithms.resources.*;
import com.srh.api.builder.AlgorithmBuilder;
import com.srh.api.builder.RecommendationBuilder;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.Algorithm;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Collaborative implements RecommendationAlgorithm {
    @Autowired
    private BasicBaseMatrix primaryMatrix;

    @Autowired
    private RecommendationMatrix recommendationMatrix;

    @Autowired
    private RecommendationUtils recommendationUtils;

    private final List<RecommendationsByEvaluator> recommendationsByEvaluators = new ArrayList<>();

    @Override
    public List<RecommendationsByEvaluator> calc(RecommendationForm form) {
        recommendationUtils.defineDecimalPrecision(form.getDecimalPrecision());
        buildBaseMatrix(form.getProjectId());

        for (Evaluator evaluator : primaryMatrix.getEvaluators()) {
            recommendationUtils.calculateRecommendationByEvaluator(evaluator, primaryMatrix);
            addRecommendationsToList(evaluator);
        }

        mountRecommendationMatrix(form.getProjectId());
        return recommendationsByEvaluators;
    }

    private void buildBaseMatrix(Integer projectId) {
        primaryMatrix.build(projectId);
    }

    private void mountRecommendationMatrix(Integer projectId) {
        recommendationMatrix.setContent(recommendationUtils.getRecommendationMatrixContent());
    }

    private void addRecommendationsToList(Evaluator evaluator) {
        RecommendationsByEvaluator recommendationsByEvaluator = new RecommendationsByEvaluator();
        List<Recommendation> recommendations = new ArrayList<>();
        recommendationsByEvaluator.setEvaluator(evaluator);

        List<CellPosition> positions = recommendationUtils
                .getRecommendationPositions()
                .stream()
                .filter(position -> position.getRow() == primaryMatrix.getEvaluators().indexOf(evaluator))
                .collect(Collectors.toList());

        for(CellPosition position: positions) {
            recommendations.add(getRecommendationByCoordinate(position));
        }

        if (positions.size() > 0) {
            recommendationsByEvaluator.setRecommendations(recommendations);
            recommendationsByEvaluators.add(recommendationsByEvaluator);
        }
    }

    private Recommendation getRecommendationByCoordinate(CellPosition cellPosition) {
        Double score = recommendationUtils
                .getRecommendationMatrixContent()[cellPosition.getRow()][cellPosition.getColumn()];

        Algorithm algorithm = AlgorithmBuilder.anAlgorithm()
                .withId(1)
                .build();

        return RecommendationBuilder.aRecommendation()
                .withDate(LocalDateTime.now())
                .withMatrixId(1)
                .withEvaluator(primaryMatrix.getEvaluators().get(cellPosition.getRow()))
                .withItem(primaryMatrix.getItems().get(cellPosition.getColumn()))
                .withAlgorithm(algorithm)
                .withWeight(score)
                .withRuntimeInSeconds(1)
                .build();
    }
}
