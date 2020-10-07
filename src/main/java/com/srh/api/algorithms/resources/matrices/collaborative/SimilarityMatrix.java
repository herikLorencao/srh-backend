package com.srh.api.algorithms.resources.matrices.collaborative;

import com.srh.api.algorithms.math.Coordinate;
import com.srh.api.algorithms.math.EuclidianDistance;
import com.srh.api.algorithms.math.MathUtil;
import com.srh.api.model.*;
import com.srh.api.service.ItemRatingService;
import com.srh.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimilarityMatrix {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ItemRatingService itemRatingService;

    private Double[][] primaryMatrix;
    List<Double[][]> similaritiesMatrix = new ArrayList<>();

    private Project project;
    private List<Evaluator> evaluators;
    private List<Item> items;
    private List<ItemRating> itemRatings;

    private Integer rowSize;
    private Integer colSize;
    private final Integer extraColSize = 2;

    public void fill(Integer projectId) {
        project = projectService.find(projectId);
        evaluators = project.getEvaluators();
        items = project.getItens();
        itemRatings = projectService.listItemRatingsByProject(projectId);
    }

    public Object generateSimilarity() {
        rowSize = evaluators.size();
        colSize = items.size();

        fillPrimaryMatrix();

//        if (primaryMatrix != null && primaryMatrix.length > 0)
//            return calculateSimilarityRows();

        return primaryMatrix;
    }

    private void fillPrimaryMatrix() {
        primaryMatrix = new Double[rowSize][colSize + extraColSize];

        for(int i = 0; i < rowSize; i++) {
            for(int j = 0; j < colSize; j++) {
                primaryMatrix[i][j] = getItemRatingInPosition(i, j);
            }
        }
    }

    private Double getItemRatingInPosition(Integer x, Integer y) {
        ItemRatingPK id = buildItemRatingId(x, y);

        try {
            ItemRating itemRating = itemRatingService.find(id);
            return itemRating.getScore();
        } catch (Exception e) {
            return null;
        }
    }

    private ItemRatingPK buildItemRatingId(Integer evaluatorPosition, Integer itemPosition) {
        ItemRatingPK id = new ItemRatingPK();

        id.setEvaluator(evaluators.get(evaluatorPosition));
        id.setItem(items.get(itemPosition));

        return id;
    }

    private Object calculateSimilarityRows() {
        Double[][] similarityMatrix = primaryMatrix;

        return null;
    }

    private List<Coordinate> calculateSimilarityCoordinate(Integer x, Integer y) {
        List<Coordinate> coordinates = new ArrayList<>();

        for(int i = 0; i < rowSize; i++) {

            if (primaryMatrix[x][y] != null && primaryMatrix[i][y] != null) {
                Coordinate coordinate = new Coordinate();
                coordinate.setX(primaryMatrix[x][y]);
                coordinate.setY(primaryMatrix[i][y]);

                coordinates.add(coordinate);
            }

        }

        return coordinates;
    }

    private void defineDistance(Integer row, Double distance) {
        if (primaryMatrix[row].length >= colSize)
            primaryMatrix[row][colSize] = distance;

        if (primaryMatrix[row].length >= colSize + 1)
            primaryMatrix[row][colSize + 1] = MathUtil.calculateSimilarity(distance);
    }

    public Double[][] getPrimaryMatrix() {
        return primaryMatrix;
    }
}
