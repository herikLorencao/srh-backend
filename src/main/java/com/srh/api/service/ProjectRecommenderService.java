package com.srh.api.service;

import com.srh.api.error.exception.DuplicateValueException;
import com.srh.api.error.exception.RelationshipNotFoundException;
import com.srh.api.model.Project;
import com.srh.api.model.ProjectRecommender;
import com.srh.api.model.Recommender;
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
    private RecommenderService recommenderService;

    public Recommender findRecommenderByProject(Integer projectId, Integer recommenderId) {
        Project project = projectService.find(projectId);
        Recommender recommender = recommenderService.find(recommenderId);

        if (project.getRecommenders().contains(recommender)) {
            return recommender;
        }

        throw new ObjectNotFoundException(recommenderId, Recommender.class.getName());
    }

    public Page<Recommender> listRecommendersByProject(Integer projectId, Pageable pageInfo) {
        Project project = projectService.find(projectId);
        List<Recommender> recommenderList = project.getRecommenders();

        PageUtil<Recommender> pageUtil = new PageUtil<>(pageInfo, recommenderList);
        return pageUtil.getPage();
    }

    @SneakyThrows
    public ProjectRecommender save(Integer projectId, Integer recommenderId) {
        Project project = projectService.find(projectId);
        Recommender recommender = recommenderService.find(recommenderId);

        ProjectRecommender projectRecommender = new ProjectRecommender(project, recommender);
        projectRecommender.addEntities();
        persistEntities(projectRecommender);

        return projectRecommender;
    }

    @SneakyThrows
    public void delete(Integer projectId, Integer recommenderId) {
        Project project = projectService.find(projectId);
        Recommender recommender = recommenderService.find(recommenderId);

        ProjectRecommender projectRecommender = new ProjectRecommender(project, recommender);
        projectRecommender.removeEntities();
        persistEntities(projectRecommender);
    }

    private void persistEntities(ProjectRecommender projectRecommender) {
        projectService.save(projectRecommender.getProject());
        recommenderService.save(projectRecommender.getRecommender());
    }
}
