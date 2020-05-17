package com.srh.api.dto.resource;

import com.srh.api.model.UserRecommendation;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "recommenders")
public class UserRecommendationDto {
    private final Integer id;
    private final String name;
    private final String login;

    public UserRecommendationDto(UserRecommendation userRecommendation) {
        this.id = userRecommendation.getId();
        this.name = userRecommendation.getName();
        this.login = userRecommendation.getLogin();
    }

    public static Page<UserRecommendationDto> convert(Page<UserRecommendation> users) {
        return users.map(UserRecommendationDto::new);
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
