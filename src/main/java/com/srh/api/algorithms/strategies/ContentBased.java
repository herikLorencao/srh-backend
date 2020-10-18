package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.resources.*;
import com.srh.api.algorithms.resources.basedcontent.EvaluatorProfileMatrix;
import com.srh.api.algorithms.resources.basedcontent.ItemTagMatrix;
import com.srh.api.algorithms.resources.basedcontent.SimilarityContent;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.Evaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
//    public List<RecommendationsByEvaluator> calc(RecommendationForm form) {
    public Object calc(RecommendationForm form) {
        buildBasicMatrix(form.getProjectId());
        itemTagMatrix.build(primaryMatrix.getItems());

        for(Evaluator evaluator: primaryMatrix.getEvaluators()) {
            EvaluatorProfileMatrix evaluatorProfileMatrix = mountEvaluatorProfile(evaluator);
            return calcRec(evaluatorProfileMatrix);
        }

        return primaryMatrix.getProject();
    }

    private void buildBasicMatrix(Integer projectId) {
        primaryMatrix.build(projectId);
    }

    private EvaluatorProfileMatrix mountEvaluatorProfile(Evaluator evaluator) {
        EvaluatorProfileMatrix evaluatorProfileMatrix = new EvaluatorProfileMatrix();
        evaluatorProfileMatrix.build(evaluator, primaryMatrix, itemTagMatrix);
        return evaluatorProfileMatrix;
    }

    private Object calcRec(EvaluatorProfileMatrix evaluatorProfileMatrix) {
        SimilarityContent similarityContent = new SimilarityContent(evaluatorProfileMatrix);
        return similarityContent.getContent();
    }
}
