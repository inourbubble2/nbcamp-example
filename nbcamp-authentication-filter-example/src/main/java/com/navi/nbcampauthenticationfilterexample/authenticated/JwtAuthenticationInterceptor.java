package com.navi.nbcampauthenticationfilterexample.authenticated;

import com.navi.nbcampauthenticationfilterexample.entity.User;
import com.navi.nbcampauthenticationfilterexample.repository.UserRepository;
import com.navi.nbcampauthenticationfilterexample.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Authenticated authenticated = handlerMethod.getMethodAnnotation(Authenticated.class);
        if (authenticated == null) {
            return true;
        }

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String accessToken = header.substring(7);
        String subject = jwtUtil.extractSubject(accessToken);
        User user = userRepository.findById(Long.parseLong(subject))
            .orElseThrow();

        if (!authenticated.role().equals(user.getRole())) {
            throw new IllegalStateException("권한이 없습니다.");
        }

        request.setAttribute("user", user);

        return true;
    }
}
