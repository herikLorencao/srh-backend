package com.srh.api.service;

import com.srh.api.error.exception.DuplicateValueException;
import com.srh.api.model.Project;
import com.srh.api.model.ProjectRecommender;
import com.srh.api.model.Recommender;
import com.srh.api.utils.PageUtil;
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

    public Page<Recommender> listRecommendersByProject(Integer projectId, Pageable pageInfo) {
        Project project = projectService.find(projectId);
        List<Recommender> recommenderList = project.getRecommenders();

        PageUtil<Recommender> pageUtil = new PageUtil(pageInfo, recommenderList);
        return pageUtil.getPage();
    }

    public ProjectRecommender save(Integer projectId, Integer recommenderId) throws DuplicateValueException {
        Project project = projectService.find(projectId);
        Recommender recommender = recommenderService.find(recommenderId);

        ProjectRecommender projectRecommender = new ProjectRecommender(project, recommender);
        projectRecommender.addEntities();

        projectService.save(projectRecommender.getProject());
        recommenderService.save(projectRecommender.getRecommender());

        return projectRecommender;
    }
}
