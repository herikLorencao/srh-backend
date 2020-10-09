package com.srh.api.algorithms.resources;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RecommendationUtils {
    public static Integer defineDecimalPrecision(Integer decimalPrecision) {
        if (decimalPrecision == null) {
            return 2;
        }
        return decimalPrecision;
    }

    public static Double roundValue(Double value, Integer decimalPrecision) {
        return BigDecimal.valueOf(value)
                .setScale(decimalPrecision, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
