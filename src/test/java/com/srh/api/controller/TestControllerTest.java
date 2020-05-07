package com.srh.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.srh.api.utils.RequestTokenUtil;
import com.srh.api.utils.UrlUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.srh.api.utils.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    Integer port;

    private HttpEntity<Void> validHeader;
    private HttpEntity<Void> invalidHeader;

    @BeforeEach
    public void configureValidHeader() throws JsonProcessingException {
        RequestTokenUtil requestTokenUtil = new RequestTokenUtil(restTemplate, port);
        validHeader = requestTokenUtil.generateValidLoginHeaders();
    }

    @BeforeEach
    public void configureInvalidHeader() throws JsonProcessingException {
        RequestTokenUtil requestTokenUtil = new RequestTokenUtil(restTemplate, port);
        invalidHeader = requestTokenUtil.generateInvalidLoginHeaders();
    }

    @Test
    public void getWithValidTokenThenSuccess() {
        String url = UrlUtils.generateBasicUrl("/test", port);
        ResponseEntity<String> response = restTemplate.exchange(url, GET, validHeader, String.class);
        assertThat(response.getBody()).isEqualTo("Hello World");
    }

    @Test
    public void getWithInvalidTokenThenForbidden() {
        String url = UrlUtils.generateBasicUrl("/test", port);
        ResponseEntity<String> response = restTemplate.exchange(url, GET, invalidHeader, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
