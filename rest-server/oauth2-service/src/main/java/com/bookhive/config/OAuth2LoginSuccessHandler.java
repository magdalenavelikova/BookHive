package com.bookhive.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final String URL = "http://localhost:3000";


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        if ("github".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())
                || "google".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())
                || "facebook".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())) {
            DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
            Map<String, Object> attributes = principal.getAttributes();
            String email = attributes.getOrDefault("email", "").toString();
            String name = attributes.getOrDefault("name", "").toString();
//            Optional<UserEntity> user = this.userService.findByEmail(email);
//            if (user.isPresent()) {
//                authUser(user.get(), attributes, user.get(), oAuth2AuthenticationToken);
//            } else {
//                saveAndAuthNewUser(oAuth2AuthenticationToken, attributes, email, name);
//            }
        }
        this.setAlwaysUseDefaultTargetUrl(true);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
