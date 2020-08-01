package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.AlgorithmCalc;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Item;

import java.util.ArrayList;
import java.util.List;

public class Collaborative implements AlgorithmCalc {
    @Override
    public List<List<Double>> calc(Double passingScore, List<Item> items, List<Evaluator> evaluators) {
        List<List<Double>> result = new ArrayList<>();
        return result;
    }
}
