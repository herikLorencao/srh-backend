package com.srh.api.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Algorithm {
    private Integer id;
    private String name;
    private TypeRecommendation typeRecommendation;

    @OneToMany(mappedBy = "algorithm")
    private List<Recommendation> recommendations;
}
