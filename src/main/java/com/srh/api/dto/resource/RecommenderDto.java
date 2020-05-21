package com.srh.api.dto.resource;

import com.srh.api.model.Recommender;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "recommenders")
public class RecommenderDto {
    private final Integer id;
    private final String name;
    private final String login;

    public RecommenderDto(Recommender recommender) {
        this.id = recommender.getId();
        this.name = recommender.getName();
        this.login = recommender.getLogin();
    }

    public static Page<RecommenderDto> convert(Page<Recommender> users) {
        return users.map(RecommenderDto::new);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
}
