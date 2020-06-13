package com.srh.api.service;

import com.srh.api.model.Project;
import com.srh.api.model.ProjectRecommender;
import com.srh.api.model.Evaluator;
import com.srh.api.utils.PageUtil;
import lombok.SneakyThrows;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectRecommenderService {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private EvaluatorService evaluatorService;

    public Evaluator findRecommenderByProject(Integer projectId, Integer recommenderId) {
        Project project = projectService.find(projectId);
        Evaluator evaluator = evaluatorService.find(recommenderId);

        if (project.getEvaluators().contains(evaluator)) {
            return evaluator;
        }

        throw new ObjectNotFoundException(recommenderId, Evaluator.class.getName());
    }

    public Page<Evaluator> listRecommendersByProject(Integer projectId, Pageable pageInfo) {
        Project project = projectService.find(projectId);
        List<Evaluator> evaluatorList = project.getEvaluators();

        PageUtil<Evaluator> pageUtil = new PageUtil<>(pageInfo, evaluatorList);
        return pageUtil.getPage();
    }

    @SneakyThrows
    public ProjectRecommender save(Integer projectId, Integer recommenderId) {
        Project project = projectService.find(projectId);
        Evaluator evaluator = evaluatorService.find(recommenderId);

        ProjectRecommender projectRecommender = new ProjectRecommender(project, evaluator);
        projectRecommender.addEntities();
        persistEntities(projectRecommender);

        return projectRecommender;
    }

    @SneakyThrows
    public void delete(Integer projectId, Integer recommenderId) {
        Project project = projectService.find(projectId);
        Evaluator evaluator = evaluatorService.find(recommenderId);

        ProjectRecommender projectRecommender = new ProjectRecommender(project, evaluator);
        projectRecommender.removeEntities();
        persistEntities(projectRecommender);
    }

    private void persistEntities(ProjectRecommender projectRecommender) {
        projectService.save(projectRecommender.getProject());
        evaluatorService.save(projectRecommender.getEvaluator());
    }
}
