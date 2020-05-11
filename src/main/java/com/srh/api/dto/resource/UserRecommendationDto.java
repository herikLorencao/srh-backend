package com.srh.api.dto.resource;

import com.srh.api.model.UserRecommendation;
import org.springframework.data.domain.Page;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRecommendationDto that = (UserRecommendationDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login);
    }
}
