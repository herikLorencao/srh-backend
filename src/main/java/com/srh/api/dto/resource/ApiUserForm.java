package com.srh.api.dto.resource;

import com.srh.api.builder.ApiUserBuilder;
import com.srh.api.model.ApiUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiUserForm {
    @NotEmpty
    @NotNull
    @Length(min = 3)
    private String name;

    @NotEmpty
    @NotNull
    @Length(min = 3)
    private String login;

    @NotEmpty
    @NotNull
    @Length(min = 6)
    private String password;

    private String oldPassword;

    public ApiUser build() {
        return ApiUserBuilder.anApiUser()
                .withLogin(login)
                .withName(name)
                .withPassword(new BCryptPasswordEncoder().encode(password))
                .withOldPassword(oldPassword)
                .build();
    }
}
