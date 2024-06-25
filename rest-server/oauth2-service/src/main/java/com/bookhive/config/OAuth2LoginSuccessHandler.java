package com.bookhive.config;

import com.bookhive.dto.AuthResponse;
import com.bookhive.service.OAuthService;
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

        String targetUrlWithToken = URL + "?token=" + authResponse.getAccessToken();

        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl(targetUrlWithToken);
        super.onAuthenticationSuccess(request, response, authentication);

        //TODO
//        on client side

//const urlParams = new URLSearchParams(window.location.search);
//const token = urlParams.get('token');
//
//        if (token) {
//            // Use the token for subsequent requests
//            fetch('/some-secured-endpoint', {
//                    method: 'GET',
//                    headers: {
//                'Authorization': 'Bearer ' + token
//            }
//    }).then(response => {
//                    // handle the response
//            }).catch(error => {
//                    // handle the error
//            });
//        }
    }
}
