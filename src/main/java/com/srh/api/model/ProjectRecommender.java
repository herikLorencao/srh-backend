package com.srh.api.model;

import com.srh.api.error.exception.DuplicateValueException;
import com.srh.api.error.exception.RelationshipNotFoundException;
import lombok.Data;

import java.util.List;

@Data
public class ProjectRecommender {
    private Project project;
    private Recommender recommender;

    public ProjectRecommender(Project project, Recommender recommender) {
        this.project = project;
        this.recommender = recommender;
    }

    public void addEntities() throws DuplicateValueException {
        addProjectInRecommender();
        addRecommenderInProject();
    }

    public void removeEntities() throws RelationshipNotFoundException {
        removeProjectInRecommender();
        removeRecommenderInProject();
    }

    private void addRecommenderInProject() throws DuplicateValueException {
        List<Recommender> recommendersInProject = getRecommenderListInProject();

        if (recommendersInProject.contains(recommender))
            throw new DuplicateValueException("Recommender link already exists");

        recommendersInProject.add(recommender);
    }

    private void addProjectInRecommender() throws DuplicateValueException {
        List<Project> projectsInRecommender = getProjectListInRecommender();

        if (projectsInRecommender.contains(project))
            throw new DuplicateValueException("Project link already exists");

        projectsInRecommender.add(project);
    }

    private void removeRecommenderInProject() throws RelationshipNotFoundException {
        List<Recommender> recommendersInProject = getRecommenderListInProject();

        if (!recommendersInProject.contains(recommender))
            throw new RelationshipNotFoundException("Project not exist in Recommender");

        recommendersInProject.remove(recommender);
    }

    private void removeProjectInRecommender() throws RelationshipNotFoundException {
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
