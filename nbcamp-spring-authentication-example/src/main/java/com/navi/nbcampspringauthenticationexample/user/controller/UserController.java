package com.navi.nbcampspringauthenticationexample.user.controller;

import com.navi.nbcampspringauthenticationexample.user.dto.UserLoginRequest;
import com.navi.nbcampspringauthenticationexample.user.dto.UserRegisterRequest;
import com.navi.nbcampspringauthenticationexample.user.dto.UserResponse;
import com.navi.nbcampspringauthenticationexample.user.entity.User;
import com.navi.nbcampspringauthenticationexample.user.repository.UserRepository;
import com.navi.nbcampspringauthenticationexample.user.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;


    @PostMapping("/api/users")
    public UserResponse register(@RequestBody UserRegisterRequest request) {
        // 유저 가입 시 추가 유효성 검증 추가 필요
        userRepository.findByUsername(request.username())
            .ifPresent(u -> {
                throw new IllegalArgumentException("Username already exists!");
            });

        User user = userRepository.save(new User(request.username(), request.password()));

        return new UserResponse(user.getId(), user.getUsername());
    }

    @PostMapping("/api/users/login")
    public String login(@RequestBody UserLoginRequest request) {
        User user = userRepository.findByUsernameAndPassword(request.username(), request.password()).orElseThrow();
        return jwtUtil.generateAccessToken(user);
    }

    @GetMapping("/api/users/")
    public List<UserResponse> getUsers() {
        return userRepository.findAll()
            .stream()
            .map(u -> new UserResponse(u.getId(), u.getUsername()))
            .toList();
    }

    @GetMapping("/api/users/authenticated")
    public UserResponse getAuthenticatedUser(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return new UserResponse(user.getId(), user.getUsername());
    }
}
