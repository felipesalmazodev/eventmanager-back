package com.zcam.eventmanager.auth;

import com.zcam.eventmanager.auth.controller.documentation.AuthDocumentation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController implements AuthDocumentation {

    @GetMapping("/api/auth/me")
    public Map<String, Object> checkMe(Authentication authentication) {
        return Map.of(
                "authenticated", true,
                "subject", authentication.getName()
        );
    }
}
