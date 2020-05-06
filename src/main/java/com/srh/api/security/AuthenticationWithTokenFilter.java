package com.srh.api.security;

import com.srh.api.error.exception.InvalidTokenException;
import com.srh.api.model.UserApi;
import com.srh.api.repository.UserApiRepository;
import com.srh.api.service.JWTService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.srh.api.security.SecurityConstants.HEADER;

public class AuthenticationWithTokenFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserApiRepository repository;

    public AuthenticationWithTokenFilter(JWTService jwtService, UserApiRepository repository) {
        this.jwtService = jwtService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);
        boolean isValidToken = jwtService.isValidJWT(token);

        if (isValidToken) {
            authenticatedClient(token);
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER);

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7);
    }

    private void authenticatedClient(String token) {
        UsernamePasswordAuthenticationToken authenticationToken;
        Integer userId = jwtService.getUserId(token);

        Optional<UserApi> optionalUserApi = repository.findById(userId);

        if (optionalUserApi.isPresent()) {
            UserApi user = optionalUserApi.get();
            authenticationToken = new UsernamePasswordAuthenticationToken(user,
                    user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            System.out.println("usuário in'válido");
            throw new InvalidTokenException("Invalid user with jwt payload");
        }
    }
}
