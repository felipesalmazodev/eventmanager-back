package com.zcam.eventmanager.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final String successRedirect;

    public OAuth2LoginSuccessHandler(JwtService jwtService,
                                     @Value("${app.oauth2.success-redirect}") String successRedirect) {
        this.jwtService = jwtService;
        this.successRedirect = successRedirect;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        OAuth2User user = (OAuth2User) authentication.getPrincipal();
        String email = user.getAttribute("email");
        if (email == null || email.isBlank()) {
            email = String.valueOf(user.getName());
        }

        String name = user.getAttribute("name");
        String picture = user.getAttribute("picture");

        String token = jwtService.generateToken(email, Map.of(
                "name", name,
                "picture", picture
        ));

        String redirectUrl = UriComponentsBuilder
                .fromUriString(successRedirect)
                .queryParam("token", token)
                .build()
                .toUriString();

        response.sendRedirect(redirectUrl);
    }
}
