package com.srh.api.model;

import com.srh.api.error.exception.DuplicateValueException;
import com.srh.api.error.exception.RelationshipNotFoundException;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.List;

@Data
public class ProjectRecommender {
    private Project project;
    private Evaluator evaluator;

    public ProjectRecommender(Project project, Evaluator evaluator) {
        this.project = project;
        this.evaluator = evaluator;
    }

    @SneakyThrows
    public void addEntities() {
        addProjectInRecommender();
        addRecommenderInProject();
    }

    @SneakyThrows
    public void removeEntities() {
        removeProjectInRecommender();
        removeRecommenderInProject();
    }

    @SneakyThrows
    private void addRecommenderInProject() {
        List<Evaluator> recommendersInProject = getRecommenderListInProject();

        if (recommendersInProject.contains(evaluator))
            throw new DuplicateValueException("Recommender link already exists");

        recommendersInProject.add(evaluator);
    }

    @SneakyThrows
    private void addProjectInRecommender() {
        List<Project> projectsInRecommender = getProjectListInRecommender();

        if (projectsInRecommender.contains(project))
            throw new DuplicateValueException("Project link already exists");

        projectsInRecommender.add(project);
    }

    @SneakyThrows
    private void removeRecommenderInProject() {
        List<Evaluator> recommendersInProject = getRecommenderListInProject();

        if (!recommendersInProject.contains(evaluator))
            throw new RelationshipNotFoundException("Project not exist in Recommender");

        recommendersInProject.remove(evaluator);
    }

    @SneakyThrows
    private void removeProjectInRecommender() {
        List<Project> projectsInRecommender = getProjectListInRecommender();

        if (!projectsInRecommender.contains(project))
            throw new RelationshipNotFoundException("Recommender not exist in Project");

        projectsInRecommender.remove(project);
    }

    private List<Evaluator> getRecommenderListInProject() {
        return project.getEvaluators();
    }

    private List<Project> getProjectListInRecommender() {
        return evaluator.getProjects();
    }
}
