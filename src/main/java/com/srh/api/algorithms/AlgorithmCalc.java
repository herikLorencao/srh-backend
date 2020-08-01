package com.srh.api.algorithms;

import com.srh.api.model.Evaluator;
import com.srh.api.model.Item;

import java.util.List;

public interface AlgorithmCalc {
    public List<List<Double>> calc(Double passingScore, List<Item> items, List<Evaluator> evaluators);
}
