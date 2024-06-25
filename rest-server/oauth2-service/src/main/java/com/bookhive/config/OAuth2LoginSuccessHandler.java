package com.bookhive.config;

import com.bookhive.dto.AuthResponse;
import com.bookhive.service.OAuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Map;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final OAuthService oAuthService;

    private static final String URL = "http://localhost:8080/users/secured";

    public OAuth2LoginSuccessHandler(OAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        AuthResponse authResponse =new AuthResponse();
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        if ("github".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())
                || "google".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())
                || "facebook".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())) {
            DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
            Map<String, Object> attributes = principal.getAttributes();

            authResponse = this.oAuthService.loginUserWithOAuth(attributes);
        }
        this.setAlwaysUseDefaultTargetUrl(true);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authResponse.getAccessToken());
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>( headers);
        this.setDefaultTargetUrl(URL);
        super.onAuthenticationSuccess(request, response, authentication);

    }
}
