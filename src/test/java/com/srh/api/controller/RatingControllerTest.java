//package com.srh.api.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.srh.api.dto.resource.RatingForm;
//import com.srh.api.model.Rating;
//import com.srh.api.repository.RatingRepository;
//import com.srh.api.utils.RequestTokenUtil;
//import com.srh.api.utils.UrlUtils;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//import static com.srh.api.utils.JsonUtil.toJson;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.isA;
//import static org.mockito.Mockito.when;
//import static org.springframework.http.HttpMethod.*;
//import static org.springframework.http.HttpStatus.*;
//import static org.springframework.http.HttpStatus.FORBIDDEN;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class RatingControllerTest {
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @LocalServerPort
//    Integer port;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private RatingRepository ratingRepository;
//
//    private HttpEntity<Void> validHeader;
//    private HttpEntity<Void> invalidHeader;
//
//    @BeforeEach
//    public void configureValidHeader() throws JsonProcessingException {
//        RequestTokenUtil requestTokenUtil = new RequestTokenUtil(restTemplate, port);
//        validHeader = requestTokenUtil.generateValidLoginHeaders();
//        invalidHeader = requestTokenUtil.generateInvalidLoginHeaders();
//    }
//
//    @BeforeEach
//    public void setup() {
//        List<Rating> ratings = Arrays.asList(
//                new Rating(1, 0.5, LocalDateTime.now(), null, null),
//                new Rating(2, 0.6, LocalDateTime.now(), null, null),
//                new Rating(3, 0.7, LocalDateTime.now(), null, null)
//        );
//
//        Page<Rating> pageRatings = new PageImpl<>(ratings);
//
//        when(ratingRepository.findAll(isA(Pageable.class))).thenReturn(pageRatings);
//        when(ratingRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(ratings.get(0)));
//        when(ratingRepository.save(isA(Rating.class))).thenReturn(ratings.get(0));
//    }
//
//    @Test
//    public void WhenGetAllRatingsThenStatusCodeOk() {
//        String url = UrlUtils.generateBasicUrl("/ratings", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenGetRatingThenStatusCodeOk() {
//        String url = UrlUtils.generateBasicUrl("/ratings/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenInsertRatingThenStatusCodeCreated() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/ratings", port);
//        RatingForm ratingForm = new RatingForm(0.5);
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(ratingForm), validHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(CREATED);
//    }
//
//    @Test
//    public void WhenUpdateRatingThenStatusCodeOk() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/ratings/1", port);
//        RatingForm ratingForm = new RatingForm(0.5);
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(ratingForm), validHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    public void WhenDeleteRatingThenStatusCodeNoContent() {
//        String url = UrlUtils.generateBasicUrl("/ratings/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, validHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
//    }
//
//    @Test
//    public void WhenGetAllRatingsWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/ratings", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenGetRatingWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/ratings/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenInsertRatingWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/ratings", port);
//        RatingForm ratingForm = new RatingForm(0.5);
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(ratingForm), invalidHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, POST, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenUpdateRatingWithInvalidTokenThenStatusCodeForbidden() throws JsonProcessingException {
//        String url = UrlUtils.generateBasicUrl("/ratings/1", port);
//        RatingForm ratingForm = new RatingForm(0.5);
//
//        HttpEntity<String> request = new HttpEntity<>(toJson(ratingForm), invalidHeader.getHeaders());
//
//        ResponseEntity<String> response = restTemplate.exchange(url, PUT, request, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//
//    @Test
//    public void WhenDeleteRatingWithInvalidTokenThenStatusCodeForbidden() {
//        String url = UrlUtils.generateBasicUrl("/ratings/1", port);
//        ResponseEntity<String> response = restTemplate.exchange(url, DELETE, invalidHeader, String.class);
//        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
//    }
//}
