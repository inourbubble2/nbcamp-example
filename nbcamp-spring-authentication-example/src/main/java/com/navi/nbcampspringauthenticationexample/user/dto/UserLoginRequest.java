package com.navi.nbcampspringauthenticationexample.user.dto;

public record UserLoginRequest(
    String username,
    String password
) {

}
