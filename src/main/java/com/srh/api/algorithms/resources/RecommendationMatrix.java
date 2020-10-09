package com.srh.api.algorithms.resources;

import com.srh.api.model.Project;
import com.srh.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecommendationMatrix {

    @Autowired
    private ProjectService projectService;

    private Double[][] content;
    private Integer matrixId;

    public Double[][] getContent() {
        return content;
    }

    public void setContent(Double[][] content) {
        this.content = content;
    }

    public void setMatrixIdByProjectId(Integer projectId) {
        Project project = projectService.find(projectId);
        matrixId = project.getLastMatrixId() + 1;
    }
}
