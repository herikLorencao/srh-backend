package com.srh.api.model;

import com.srh.api.error.exception.DuplicateValueException;
import com.srh.api.error.exception.RelationshipNotFoundException;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.List;

@Data
public class ProjectRecommender {
    private Project project;
    private Recommender recommender;

    public ProjectRecommender(Project project, Recommender recommender) {
        this.project = project;
        this.recommender = recommender;
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
        List<Recommender> recommendersInProject = getRecommenderListInProject();

        if (recommendersInProject.contains(recommender))
            throw new DuplicateValueException("Recommender link already exists");

        recommendersInProject.add(recommender);
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
        List<Recommender> recommendersInProject = getRecommenderListInProject();

        if (!recommendersInProject.contains(recommender))
            throw new RelationshipNotFoundException("Project not exist in Recommender");

        recommendersInProject.remove(recommender);
    }

    @SneakyThrows
    private void removeProjectInRecommender() {
        List<Project> projectsInRecommender = getProjectListInRecommender();

        if (!projectsInRecommender.contains(project))
            throw new RelationshipNotFoundException("Recommender not exist in Project");

        projectsInRecommender.remove(project);
    }

    private List<Recommender> getRecommenderListInProject() {
        return project.getRecommenders();
    }

    private List<Project> getProjectListInRecommender() {
        return recommender.getProjects();
    }
}
