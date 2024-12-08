package com.navi.nbcampspringsecurityoauthexample.config;

import com.navi.nbcampspringsecurityoauthexample.oauth.OAuthLoginSuccessHandler;
import com.navi.nbcampspringsecurityoauthexample.oauth.OAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuthUserService oAuthUserService;

    private final OAuthLoginSuccessHandler oAuthLoginSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            authorize -> authorize.anyRequest().authenticated()
        );

        http.oauth2Login(oauth ->
            oauth
                .userInfoEndpoint(c -> c.userService(oAuthUserService))
                .successHandler(oAuthLoginSuccessHandler)
        );

        return http.build();
    }

}
