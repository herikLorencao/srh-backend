package com.srh.api.dto.resource;

import com.srh.api.builder.RecommenderBuilder;
import com.srh.api.model.Recommender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecommenderForm {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    @Length(min = 6)
    private String password;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    private String oldPassword;

    public Recommender build() {
        return RecommenderBuilder.aRecommender()
                .withName(name)
                .withLogin(login)
                .withPassword(new BCryptPasswordEncoder().encode(password))
                .withOldPassword(oldPassword)
                .withEmail(email)
                .build();
    }
}
