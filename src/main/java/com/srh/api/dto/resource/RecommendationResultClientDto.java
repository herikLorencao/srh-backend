package com.srh.api.dto.resource;

import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Getter
@Relation(collectionRelation = "recommendation results")
public class RecommendationResultClientDto {
    List<ListRecommendationsByUserDto> recommendations;
}
