package com.navi.nbcampauthenticationfilterexample.config;

import com.navi.nbcampauthenticationfilterexample.authenticated.AuthenticatedUserArgumentResolver;
import com.navi.nbcampauthenticationfilterexample.authenticated.JwtAuthenticationInterceptor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtAuthenticationInterceptor jwtAuthenticationInterceptor;
    private final AuthenticatedUserArgumentResolver authenticatedUserArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtAuthenticationInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authenticatedUserArgumentResolver);
    }
}
