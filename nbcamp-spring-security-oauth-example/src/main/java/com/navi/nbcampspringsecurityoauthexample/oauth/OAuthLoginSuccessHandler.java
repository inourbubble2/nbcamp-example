package com.navi.nbcampspringsecurityoauthexample.oauth;

import com.navi.nbcampspringsecurityoauthexample.oauth.model.OAuthUser;
import com.navi.nbcampspringsecurityoauthexample.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {
        OAuthUser oAuthUser = (OAuthUser) authentication.getPrincipal();
        log.info("Logged in user {} by {}", oAuthUser.getProviderId(), oAuthUser.getProvider());

        String accessToken = jwtUtil.generateAccessToken(oAuthUser.getMemberId());

        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.getWriter().write(accessToken);
    }
}
