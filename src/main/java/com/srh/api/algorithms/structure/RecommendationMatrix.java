package com.srh.api.algorithms.structure;

import com.srh.api.model.RecommendationResultMatrix;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class RecommendationMatrix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private RecommendationResultMatrix recommendationResultMatrix;

    private List<String> labelRows;
    private List<String> labelColumns;
    private List<List<Double>> matrix;
}
