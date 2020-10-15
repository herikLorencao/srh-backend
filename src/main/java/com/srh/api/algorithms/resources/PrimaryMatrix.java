package com.srh.api.algorithms.resources;

import com.srh.api.model.Evaluator;
import com.srh.api.model.Project;

import java.util.List;

public abstract class PrimaryMatrix {
    protected Double[][] content;

    protected Project project;
    protected List<Evaluator> evaluators;
    protected Integer rowSize;
    protected Integer colSize;

    public void build(Integer projectId) {}

    public Double[][] getContent() {
        return content;
    }

    public Project getProject() {
        return project;
    }

    public List<Evaluator> getEvaluators() {
        return evaluators;
    }

    public Integer getRowSize() {
        return rowSize;
    }

    public Integer getColSize() {
        return colSize;
    }
}
