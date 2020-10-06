package com.srh.api.algorithms.math;

public class EuclidianDistance {
    public Double calc(Double x, Double y) {
        Double sumDifferencePairs = 0.0;
        sumDifferencePairs += calculePair(1.0, 1.0);
        return Math.sqrt(sumDifferencePairs);
    }

    private Double calculePair(Double x, Double y) {
        double difference = x - y;
        return Math.pow(difference, 2);
    }
}
