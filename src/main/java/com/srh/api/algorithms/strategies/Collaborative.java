package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.math.Coordinate;
import com.srh.api.algorithms.resources.RecommendationAlgorithm;
import com.srh.api.algorithms.resources.collaborative.PrimaryMatrix;
import com.srh.api.algorithms.resources.RecommendationMatrix;
import com.srh.api.algorithms.resources.collaborative.SimilarityMatrixEvaluator;
import com.srh.api.algorithms.resources.collaborative.SimilarityMatrix;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Project;
import com.srh.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Collaborative implements RecommendationAlgorithm {
    @Autowired
    private PrimaryMatrix primaryMatrix;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RecommendationMatrix recommendationMatrix;

    private Double[][] recommendationMatrixContent;
    List<Coordinate> recommendationCoordinates = new ArrayList<>();

    @Override
    public Object calc(RecommendationForm form) {
        primaryMatrix.build(form.getProjectId());
        recommendationMatrix.setMatrixIdByProjectId(form.getProjectId());
        recommendationMatrixContent = primaryMatrix.getContent();

        for(Evaluator evaluator: primaryMatrix.getEvaluators()) {
           calculateRecommendationByEvaluator(evaluator);
        }

        return recommendationMatrixContent;
    }

    private void calculateRecommendationByEvaluator(Evaluator evaluator) {
        SimilarityMatrix similarityMatrix = new SimilarityMatrix(primaryMatrix, evaluator);
        SimilarityMatrixEvaluator similarityMatrixEvaluator = new SimilarityMatrixEvaluator(
                similarityMatrix, evaluator);

        for(int i = 0; i < primaryMatrix.getRowSize(); i++) {
            for(int j = 0; j < primaryMatrix.getColSize(); j++) {
                calculateRecommendationByCell(i, j, similarityMatrix, similarityMatrixEvaluator);
            }
        }
    }

    private void calculateRecommendationByCell(Integer row, Integer column, SimilarityMatrix similarityMatrix,
        SimilarityMatrixEvaluator similarityMatrixEvaluator) {
        if (primaryMatrix.getContent()[row][column] != null)
            return;

        registerRecommendationCoordinate(row, column);
        recommendationMatrixContent[row][column] = calculateRecommendationValue(column, similarityMatrix,
                similarityMatrixEvaluator);
    }

    private Double calculateRecommendationValue(Integer column, SimilarityMatrix similarityMatrix,
                                                SimilarityMatrixEvaluator similarityMatrixEvaluator) {
        Double similarityItemSum = getSimilarityItemSum(column, similarityMatrixEvaluator);
        Double similaritySum = getSimilaritySum(column, similarityMatrix);

        if (similaritySum != 0) {
            return similarityItemSum/similaritySum;
        }

        System.out.println(similaritySum + " : " + similarityItemSum);
        return 0.0;
    }

    private void registerRecommendationCoordinate(Integer row, Integer column) {
        Coordinate coordinate = new Coordinate();

        coordinate.setX(Double.valueOf(row));
        coordinate.setY(Double.valueOf(column));

        recommendationCoordinates.add(coordinate);
    }

    private Integer getNewMatrixId(Integer projectId) {
        Project project = projectService.find(projectId);
        return project.getLastMatrixId() + 1;
    }

    private Double getSimilarityItemSum(Integer column, SimilarityMatrixEvaluator similarityMatrixEvaluator) {
        Double amount = 0.0;

        for(int i = 0; i < similarityMatrixEvaluator.getRowSize(); i++) {
            if (similarityMatrixEvaluator.getContent()[i][column] == null)
                continue;
            amount += similarityMatrixEvaluator.getContent()[i][column];
        }

        return amount;
    }

    private Double getSimilaritySum(Integer column, SimilarityMatrix similarityMatrix) {
        Double amount = 0.0;
        Integer similarityIndex = similarityMatrix.getColSize() - 1;

        for(int i = 0; i < similarityMatrix.getRowSize(); i++) {
            if (i == similarityMatrix.getEvaluatorRow() || similarityMatrix.getContent()[i][column] == null)
                continue;

            amount += similarityMatrix.getContent()[i][similarityIndex];
        }

        return amount;
    }
}
