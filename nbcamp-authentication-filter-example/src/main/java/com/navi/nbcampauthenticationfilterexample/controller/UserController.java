package com.navi.nbcampauthenticationfilterexample.controller;

import com.navi.nbcampauthenticationfilterexample.authenticated.Authenticated;
import com.navi.nbcampauthenticationfilterexample.authenticated.AuthenticatedUser;
import com.navi.nbcampauthenticationfilterexample.entity.User;
import com.navi.nbcampauthenticationfilterexample.entity.UserRole;
import com.navi.nbcampauthenticationfilterexample.model.UserLoginRequest;
import com.navi.nbcampauthenticationfilterexample.model.UserRegisterRequest;
import com.navi.nbcampauthenticationfilterexample.model.UserResponse;
import com.navi.nbcampauthenticationfilterexample.repository.UserRepository;
import com.navi.nbcampauthenticationfilterexample.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/api/user/register")
    public UserResponse register(@RequestBody UserRegisterRequest request) {
        User user = new User(
            request.username(),
            request.password(),
            request.email(),
            request.role()
        );

        userRepository.save(user);

        return new UserResponse(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }

    @PostMapping("/api/user/login")
    public String login(@RequestBody UserLoginRequest request) {
        User user = userRepository.findByUsernameAndPassword(request.username(), request.password())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return jwtUtil.generateAccessToken(user);
    }

    @Authenticated(role = UserRole.SELLER)
    @GetMapping("/api/user/authenticated")
    public UserResponse getAuthenticatedUser(@AuthenticatedUser User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
