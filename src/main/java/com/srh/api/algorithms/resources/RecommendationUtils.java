package com.srh.api.algorithms.resources;

import com.srh.api.algorithms.math.CellPosition;
import com.srh.api.algorithms.math.Coordinate;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.Evaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class RecommendationUtils {
    @Autowired
    private PrimaryMatrix primaryMatrix;

    private Double[][] recommendationMatrixContent;
    private final List<CellPosition> recommendationPositions = new ArrayList<>();
    private Integer decimalPrecision;

    public void calculateRecommendationByEvaluator(Evaluator evaluator) {
        SimilarityMatrix similarityMatrix = new SimilarityMatrix(primaryMatrix, evaluator);
        SimilarityMatrixEvaluator similarityMatrixEvaluator = new SimilarityMatrixEvaluator(
                similarityMatrix, evaluator);

        for (int i = 0; i < primaryMatrix.getRowSize(); i++) {
            for (int j = 0; j < primaryMatrix.getColSize(); j++) {
                calculateRecommendationByCell(i, j, similarityMatrix, similarityMatrixEvaluator);
            }
        }
    }

    public void configureRecommendationAlgorithm(RecommendationForm form) {
        mountPrimaryMatrices(form.getProjectId());
        defineDecimalPrecision(form.getDecimalPrecision());
        setPrimaryMatrix(primaryMatrix);
        setRecommendationMatrixContent(primaryMatrix.getContent());
    }

    private void mountPrimaryMatrices(Integer projectId) {
        primaryMatrix.build(projectId);
    }

    private void defineDecimalPrecision(Integer value) {
        if (value == null) {
            decimalPrecision = 2;
            return;
        }
        decimalPrecision = value;
    }

    private Double roundValue(Double value, Integer decimalPrecision) {
        return BigDecimal.valueOf(value)
                .setScale(decimalPrecision, RoundingMode.HALF_UP)
                .doubleValue();
    }

    private void registerRecommendationCoordinate(Integer row, Integer column) {
        CellPosition position = new CellPosition();

        position.setRow(row);
        position.setColumn(column);

        recommendationPositions.add(position);
    }

    private Double getSimilaritySum(Integer column, SimilarityMatrix similarityMatrix) {
        Double amount = 0.0;
        Integer similarityIndex = similarityMatrix.getColSize() - 1;

        for (int i = 0; i < similarityMatrix.getRowSize(); i++) {
            if (i == similarityMatrix.getEvaluatorRow() || similarityMatrix.getContent()[i][column] == null)
                continue;

            amount += similarityMatrix.getContent()[i][similarityIndex];
        }

        return amount;
    }

    private Double getSimilarityItemSum(Integer column, SimilarityMatrixEvaluator similarityMatrixEvaluator) {
        Double amount = 0.0;

        for (int i = 0; i < similarityMatrixEvaluator.getRowSize(); i++) {
            if (similarityMatrixEvaluator.getContent()[i][column] == null)
                continue;
            amount += similarityMatrixEvaluator.getContent()[i][column];
        }

        return amount;
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
        Double result = 0.0;

        if (similaritySum != 0) {
            result = similarityItemSum / similaritySum;
        }

        return roundValue(result, decimalPrecision);
    }

    public void setPrimaryMatrix(PrimaryMatrix primaryMatrix) {
        this.primaryMatrix = primaryMatrix;
    }

    public Double[][] getRecommendationMatrixContent() {
        return recommendationMatrixContent;
    }

    public void setRecommendationMatrixContent(Double[][] recommendationMatrixContent) {
        this.recommendationMatrixContent = recommendationMatrixContent;
    }

    public List<CellPosition> getRecommendationPositions() {
        return recommendationPositions;
    }
}