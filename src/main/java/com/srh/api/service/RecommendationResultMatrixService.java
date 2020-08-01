package com.srh.api.service;

import com.srh.api.algorithms.AlgorithmCalc;
import com.srh.api.algorithms.AlgorithmStrategy;
import com.srh.api.algorithms.structure.RecommendationMatrix;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Item;
import com.srh.api.model.RecommendationResultMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationResultMatrixService {
    @Autowired
    private ItemService itemService;

    @Autowired
    private EvaluatorService evaluatorService;

    @Autowired
    private RecommendationService recommendationService;

    public RecommendationResultMatrix generateRecommendation(
            RecommendationResultMatrix recommendationResultMatrixInfo) {
        List<Evaluator> evaluators = evaluatorService.findAll(Pageable.unpaged()).getContent();
        List<Item> items = itemService.findAll(Pageable.unpaged()).getContent();

        RecommendationResultMatrix recommendationResultMatrix = new RecommendationResultMatrix();
        recommendationResultMatrix.setResult(generateMatrix(
                recommendationResultMatrixInfo.getPassingScore(), items, evaluators,
                recommendationResultMatrixInfo.getAlgorithm().getId()
        ));

        return recommendationResultMatrix;
    }

    private RecommendationMatrix generateMatrix(
            Double passingScore, List<Item> items, List<Evaluator> evaluators, Integer algorithmId
    ) {
        RecommendationMatrix recommendationMatrix = new RecommendationMatrix();

        recommendationMatrix.setMatrix(calculateRecommendation(
                algorithmId, passingScore, items, evaluators
        ));
        recommendationMatrix.setLabelColumns(generateItemsLabel(items));
        recommendationMatrix.setLabelRows(generateEvaluatorsLabel(evaluators));

        return recommendationMatrix;
    }

    private List<List<Double>> calculateRecommendation(
            Integer algorithmId, Double passingScore, List<Item> items, List<Evaluator> evaluators) {
        AlgorithmCalc algorithmCalc = AlgorithmStrategy.findInstance(algorithmId);
        return algorithmCalc.calc(passingScore, items, evaluators);
    }

    private List<String> generateItemsLabel(List<Item> items) {
        List<String> labels = new ArrayList<>();
        items.forEach(item -> labels.add(item.getName()));
        return labels;
    }

    private List<String> generateEvaluatorsLabel(List<Evaluator> evaluators) {
        List<String> labels = new ArrayList<>();
        evaluators.forEach(evaluator -> labels.add(evaluator.getName()));
        return labels;
    }
}
