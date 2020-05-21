package com.srh.api.model;

import com.srh.api.error.exception.DuplicateValueException;
import org.hibernate.DuplicateMappingException;

import java.util.List;
import java.util.Objects;

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

    private List<Recommender> getRecommenderListInProject() {
        return project.getRecommenders();
    }

    private List<Project> getProjectListInRecommender() {
        return recommender.getProjects();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Recommender getRecommender() {
        return recommender;
    }

    public void setRecommender(Recommender recommender) {
        this.recommender = recommender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectRecommender that = (ProjectRecommender) o;
        return Objects.equals(project, that.project) &&
                Objects.equals(recommender, that.recommender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, recommender);
    }
}
