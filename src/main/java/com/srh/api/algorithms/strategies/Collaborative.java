package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.AlgorithmCalc;
import com.srh.api.algorithms.structure.RecommendationsByUser;
import com.srh.api.builder.RecommendationBuilder;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.*;
import com.srh.api.repository.EvaluatorRepository;
import com.srh.api.repository.ItemRepository;
import com.srh.api.repository.ProjectRepository;
import com.srh.api.repository.RecommendationRepository;
import com.srh.api.service.AlgorithmService;
import com.srh.api.service.EvaluatorService;
import com.srh.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Collaborative implements AlgorithmCalc {
    @Autowired
    private EvaluatorService evaluatorService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AlgorithmService algorithmService;

    @Override
    public List<RecommendationsByUser> calc(RecommendationForm recommendationForm) {
        Iterable<Evaluator> evaluators = evaluatorService.listAll();
        List<RecommendationsByUser> recommendations = new ArrayList<>();

        for (Evaluator evaluator : evaluators) {
            RecommendationsByUser recommendationsByUser = generateRecommendationByEvaluator(
                    evaluator, recommendationForm
            );

            if (recommendationsByUser == null) {
                recommendations.add(recommendationsByUser);
            }
        }

        return recommendations;
    }

    private RecommendationsByUser generateRecommendationByEvaluator(Evaluator evaluator,
            RecommendationForm recommendationForm) {
        Random random = new Random();

        if (random.nextInt(2) == 1) {
            RecommendationsByUser recommendationsByUser = new RecommendationsByUser();

            recommendationsByUser.setEvaluator(evaluator);
            recommendationsByUser.setRecommendations(calculateRecommendations(evaluator,
                    recommendationForm));

            return recommendationsByUser;
        }

        return null;
    }

    private List<Recommendation> calculateRecommendations(Evaluator evaluator,
                                                          RecommendationForm recommendationForm) {
        Random random = new Random();
        List<Recommendation> recommendations = new ArrayList<>();

        Project project = projectService.find(recommendationForm.getProjectId());
        Integer matrixId = project.getLastMatrixId() + 1;
        Algorithm algorithm = algorithmService.find(recommendationForm.getAlgorithmId());

        for(Item item: itemRepository.findByProject(project)) {
            Double score = random.nextDouble() * 5;

            if (score > recommendationForm.getPassingScore()) {
                Recommendation recommendation = RecommendationBuilder.aRecommendation()
                        .withAlgorithm(algorithm)
                        .withItem(item)
                        .withDate(LocalDateTime.now())
                        .withEvaluator(evaluator)
                        .withRuntimeInSeconds(1)
                        .withWeight(score)
                        .withMatrixId(matrixId)
                        .build();

                recommendationRepository.save(recommendation);
                recommendations.add(recommendation);
            }
        }

        project.setLastMatrixId(matrixId);
        projectService.save(project);

        return recommendations;
    }
}
