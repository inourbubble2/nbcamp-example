package com.navi.nbcampspringauthenticationexample.user.filter;

import com.navi.nbcampspringauthenticationexample.user.entity.User;
import com.navi.nbcampspringauthenticationexample.user.repository.UserRepository;
import com.navi.nbcampspringauthenticationexample.user.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        if (
            (request.getRequestURI().equals("/api/users") && request.getMethod().equals("POST"))
            || (request.getRequestURI().equals("/api/users/login") && request.getMethod().equals("POST"))
        ) {
            filterChain.doFilter(request, response);
        } else {
            String authorization = request.getHeader("Authorization");
            String accessToken = authorization.substring(7);
            String subject = jwtUtil.extractSubject(accessToken);

            User user = userRepository.findById(Long.parseLong(subject)).orElseThrow();

            request.setAttribute("user", user);
            filterChain.doFilter(request, response);
        }
    }
}
