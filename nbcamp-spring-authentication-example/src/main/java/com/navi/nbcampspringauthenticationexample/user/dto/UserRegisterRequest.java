package com.navi.nbcampspringauthenticationexample.user.dto;

public record UserRegisterRequest(
    Long id,
    String username,
    String password
) {

}
