package com.srh.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.srh.api.builder.LoginFormBuilder;
import com.srh.api.dto.auth.LoginForm;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static com.srh.api.utils.TestConstants.*;
import static com.srh.api.utils.TestConstants.invalidTokenPassword;

public class RequestTokenUtil {
    private final TestRestTemplate restTemplate;
    private final Integer port;

    public RequestTokenUtil(TestRestTemplate restTemplate, Integer testServerPort) {
        this.restTemplate = restTemplate;
        this.port = testServerPort;
    }

    public HttpEntity<Void> generateValidLoginHeaders() throws JsonProcessingException {
        return getTokenRequestHeaders(true);
    }

    public HttpEntity<Void> generateInvalidLoginHeaders() throws JsonProcessingException {
        /* Two login request is not possible in Spring */
        return new HttpEntity<>(defineInvalidTokenHeader());
    }

    private HttpEntity<Void> getTokenRequestHeaders(boolean validUser)
            throws JsonProcessingException {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                UrlUtils.generateBasicUrl("/auth", port),
                buildGetTokenEntityRequest(validUser),
                String.class
        );
        return new HttpEntity<>(responseEntity.getHeaders());
    }

    private HttpEntity<String> buildGetTokenEntityRequest(boolean validUser)
            throws JsonProcessingException {
        String body = buildBodyLoginRequest(validUser);
        HttpHeaders headers = defineHeaderContentTypeApplicationJson();
        return new HttpEntity<String>(body, headers);
    }

    public static String buildBodyLoginRequest(boolean validUser)
            throws JsonProcessingException {
        LoginForm loginForm;

        if (validUser) {
            loginForm = buildValidLoginForm();
        } else {
            loginForm = buildInvalidLoginForm();
        }

        return JsonUtil.toJson(loginForm);
    }

    private HttpHeaders defineHeaderContentTypeApplicationJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private HttpHeaders defineInvalidTokenHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Error");
        return headers;
    }

    private static LoginForm buildValidLoginForm() {
        return buildLoginForm(validTokenLogin, validTokenPassword);
    }

    private static LoginForm buildInvalidLoginForm() {
        return buildLoginForm(invalidTokenLogin, invalidTokenPassword);
    }

    private static LoginForm buildLoginForm(String login, String password) {
        return LoginFormBuilder
                .aLoginForm()
                .withLogin(login)
                .withPassword(password)
                .build();
    }
}
