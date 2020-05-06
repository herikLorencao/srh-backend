package com.srh.api.controller;

import com.srh.api.dto.auth.AuthDto;
import com.srh.api.dto.auth.LoginForm;
import com.srh.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping
    public ResponseEntity<AuthDto> auth(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken loginUser = form.convert();
        String token = authService.generatedTokenForAuthenticatedUser(loginUser);
        return ResponseEntity.ok(new AuthDto(token, "Bearer"));
    }
}
