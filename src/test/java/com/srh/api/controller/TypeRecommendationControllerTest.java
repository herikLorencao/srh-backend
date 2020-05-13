package com.srh.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.srh.api.dto.resource.TypeRecommendationForm;
import com.srh.api.model.TypeRecommendation;
import com.srh.api.repository.TypeRecommendationRepository;
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
public class TypeRecommendationControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    Integer port;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeRecommendationRepository typeRecommendationRepository;

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
        List<TypeRecommendation> typeRecommendations = Arrays.asList(
                new TypeRecommendation(1, "type recommendation 1", "type recommendation 1", true, null),
                new TypeRecommendation(2, "type recommendation 2", "type recommendation 2", true, null),
                new TypeRecommendation(3, "type recommendation 3", "type recommendation 3", false, null)
        );

        Page<TypeRecommendation> pageTags = new PageImpl<>(typeRecommendations);

        when(typeRecommendationRepository.findAll(isA(Pageable.class))).thenReturn(pageTags);
        when(typeRecommendationRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(typeRecommendations.get(0)));
        when(typeRecommendationRepository.save(isA(TypeRecommendation.class))).thenReturn(typeRecommendations.get(0));
    }

    @Test
    public void WhenGetAllTypeRecommendationsThenStatusCodeOk() {
        String url = UrlUtils.generateBasicUrl("/recommendations/types", port);
        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void WhenGetTypeRecommendationThenStatusCodeOk() {
        String url = UrlUtils.generateBasicUrl("/recommendations/types/1", port);
        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void WhenInsertTypeRecommendationThenStatusCodeCreated() throws JsonProcessingException {
        String url = UrlUtils.generateBasicUrl("/recommendations/types", port);
        TypeRecommendationForm typeRecommendationForm = new TypeRecommendationForm("type recommendation", "type recommendation", false);

        HttpEntity<String> request = new HttpEntity<>(toJson(typeRecommendationForm), validHeader.getHeaders());

        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(CREATED);
    }

    @Test
    public void WhenUpdateTypeRecommendationThenStatusCodeOk() throws JsonProcessingException {
        String url = UrlUtils.generateBasicUrl("/recommendations/types/1", port);
        TypeRecommendationForm typeRecommendationForm = new TypeRecommendationForm("type recommendation", "type recommendation", false);

        HttpEntity<String> request = new HttpEntity<>(toJson(typeRecommendationForm), validHeader.getHeaders());

        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void WhenDeleteTypeRecommendationThenStatusCodeNoContent() {
        String url = UrlUtils.generateBasicUrl("/recommendations/types/1", port);
        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, validHeader, String.class);
        assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
    }

    @Test
    public void WhenGetAllTypeRecommendationsWithInvalidTokenThenStatusCodeForbidden() {
        String url = UrlUtils.generateBasicUrl("/recommendations/types", port);
        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
    }

    @Test
    public void WhenGetTypeRecommendationWithInvalidTokenThenStatusCodeForbidden() {
        String url = UrlUtils.generateBasicUrl("/recommendations/types/1", port);
        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
    }

    @Test
    public void WhenInsertTypeRecommendationWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
        String url = UrlUtils.generateBasicUrl("/recommendations/types", port);
        TypeRecommendationForm typeRecommendationForm = new TypeRecommendationForm("type recommendation", "type recommendation", false);

        HttpEntity<String> request = new HttpEntity<>(toJson(typeRecommendationForm), invalidHeader.getHeaders());

        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
    }

    @Test
    public void WhenUpdateTypeRecommendationWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
        String url = UrlUtils.generateBasicUrl("/recommendations/types/1", port);
        TypeRecommendationForm typeRecommendationForm = new TypeRecommendationForm("type recommendation", "type recommendation", false);

        HttpEntity<String> request = new HttpEntity<>(toJson(typeRecommendationForm), invalidHeader.getHeaders());

        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
    }

    @Test
    public void WhenDeleteTypeRecommendationWithInvalidTokenThenStatusCodeForbidden() {
        String url = UrlUtils.generateBasicUrl("/recommendations/types/1", port);
        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, invalidHeader, String.class);
        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
    }
}
