package com.srh.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.srh.api.dto.resource.UserForm;
import com.srh.api.model.UserRecommendation;
import com.srh.api.repository.UserRecommendationRepository;
import com.srh.api.utils.RequestTokenUtil;
import com.srh.api.utils.UrlUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.srh.api.utils.JsonUtil.toJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserRecommendationControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    Integer port;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRecommendationRepository userRecommendationRepository;

    private HttpEntity<Void> validHeader;
    private HttpEntity<Void> invalidHeader;

    @BeforeEach
    public void configureValidHeader() throws JsonProcessingException {
        RequestTokenUtil requestTokenUtil = new RequestTokenUtil(restTemplate, port);
        validHeader = requestTokenUtil.generateValidLoginHeaders();
        invalidHeader = requestTokenUtil.generateInvalidLoginHeaders();
    }

    @BeforeEach
    public void setup() {
        List<UserRecommendation> users = Arrays.asList(
                new UserRecommendation(1, "user 1", "user 1", "123", null, null, null),
                new UserRecommendation(2, "user 2", "user 2", "456", null, null, null),
                new UserRecommendation(3, "user 3", "user 3", "789", null, null, null)
        );

        Page<UserRecommendation> pageUsers = new PageImpl<>(users);

        when(userRecommendationRepository.findAll(isA(Pageable.class))).thenReturn(pageUsers);
        when(userRecommendationRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(users.get(0)));
        when(userRecommendationRepository.save(isA(UserRecommendation.class))).thenReturn(users.get(0));
    }

    @Test
    public void WhenGetAllUsersThenStatusCodeOk() {
        String url = UrlUtils.generateBasicUrl("/users/recommendation", port);
        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void WhenGetUserThenStatusCodeOk() {
        String url = UrlUtils.generateBasicUrl("/users/recommendation/1", port);
        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void WhenInsertUserThenStatusCodeCreated() throws JsonProcessingException {
        String url = UrlUtils.generateBasicUrl("/users/recommendation", port);
        UserForm userForm = new UserForm("user recommendation", "user recommendation", "user test");

        HttpEntity<String> request = new HttpEntity<>(toJson(userForm), validHeader.getHeaders());

        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(CREATED);
    }

    @Test
    public void WhenUpdateUserThenStatusCodeOk() throws JsonProcessingException {
        String url = UrlUtils.generateBasicUrl("/users/recommendation/1", port);
        UserForm userForm = new UserForm("user recommendation", "user recommendation", "user test");

        HttpEntity<String> request = new HttpEntity<>(toJson(userForm), validHeader.getHeaders());

        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void WhenDeleteUserThenStatusCodeNoContent() {
        String url = UrlUtils.generateBasicUrl("/users/recommendation/1", port);
        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, validHeader, String.class);
        assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
    }

    @Test
    public void WhenGetAllUsersWithInvalidTokenThenStatusCodeForbidden() {
        String url = UrlUtils.generateBasicUrl("/users/recommendation", port);
        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
    }

    @Test
    public void WhenGetUserWithInvalidTokenThenStatusCodeForbidden() {
        String url = UrlUtils.generateBasicUrl("/users/recommendation/1", port);
        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
    }

    @Test
    public void WhenInsertUserWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
        String url = UrlUtils.generateBasicUrl("/users/recommendation", port);
        UserForm userForm = new UserForm("user recommendation", "user recommendation", "user test");

        HttpEntity<String> request = new HttpEntity<>(toJson(userForm), invalidHeader.getHeaders());

        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
    }

    @Test
    public void WhenUpdateUserWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
        String url = UrlUtils.generateBasicUrl("/users/recommendation/1", port);
        UserForm userForm = new UserForm("user recommendation", "user recommendation", "user test");

        HttpEntity<String> request = new HttpEntity<>(toJson(userForm), invalidHeader.getHeaders());

        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
    }

    @Test
    public void WhenDeleteUserWithInvalidTokenThenStatusCodeForbidden() {
        String url = UrlUtils.generateBasicUrl("/users/recommendation/1", port);
        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, invalidHeader, String.class);
        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
    }
}
