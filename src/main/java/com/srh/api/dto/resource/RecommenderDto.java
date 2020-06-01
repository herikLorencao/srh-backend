package com.srh.api.dto.resource;

import com.srh.api.model.Recommender;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "recommenders")
public class RecommenderDto {
    private final Integer id;
    private final String name;
    private final String login;
    private final String email;

    public RecommenderDto(Recommender recommender) {
        this.id = recommender.getId();
        this.name = recommender.getName();
        this.login = recommender.getLogin();
        this.email = recommender.getEmail();
    }

    public static Page<RecommenderDto> convert(Page<Recommender> users) {
        return users.map(RecommenderDto::new);
    }
}
