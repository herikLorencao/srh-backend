package com.srh.api.algorithms.resources.matrices.collaborative;

import com.srh.api.algorithms.math.Coordinate;
import com.srh.api.algorithms.math.EuclidianDistance;
import com.srh.api.algorithms.math.MathUtil;
import com.srh.api.model.Evaluator;

import java.util.ArrayList;
import java.util.List;

public class SimilarityMatrix {
    private Double[][] content;
    Integer rowSize;
    Integer colSize;
    Integer evaluatorRow;

    EuclidianDistance euclidianDistance = new EuclidianDistance();

    public SimilarityMatrix(PrimaryMatrix primaryMatrix, Evaluator evaluator) {
        rowSize = primaryMatrix.getRowSize();
        colSize = primaryMatrix.getColSize() + 2;
        evaluatorRow = getEvaluatorRow(evaluator, primaryMatrix.getEvaluators());

        build(primaryMatrix.getContent());
    }

    private void build(Double[][] primaryMatrixContent) {
        content = new Double[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            calculateCellsValues(primaryMatrixContent, i);
        }
    }

    private void calculateCellsValues(Double[][] primaryMatrix, Integer row) {
        List<Coordinate> coordinates = new ArrayList<>();

        for(int j = 0; j < colSize; j++) {
            coordinates.add(calculateCell(primaryMatrix, row, j, coordinates));
        }
    }

    private Coordinate calculateCell(Double[][] primaryMatrix, Integer row, Integer col,  List<Coordinate> coordinates) {
        Integer distanceColIndex = colSize - 2;
        Integer similarityColIndex = colSize - 1;

        if (col.equals(distanceColIndex)) {
            content[row][col] = euclidianDistance.calc(coordinates);
            return buildCoordinate(null, null);
        }

        if (col.equals(similarityColIndex)) {
            content[row][col] = MathUtil.calculateSimilarity(euclidianDistance.calc(coordinates));
            return buildCoordinate(null, null);
        }

        content[row][col] = primaryMatrix[row][col];
        return buildCoordinate(primaryMatrix[evaluatorRow][col], content[row][col]);
    }

    private Coordinate buildCoordinate(Double x, Double y) {
        Coordinate coordinate = new Coordinate();

        coordinate.setX(x);
        coordinate.setY(y);

        return coordinate;
    }

    private Integer getEvaluatorRow(Evaluator evaluator, List<Evaluator> evaluators) {
        return evaluators.indexOf(evaluator);
    }

    public Double[][] getContent() {
        return content;
    }
}
