package com.navi.nbcampauthenticationfilterexample.model;

import com.navi.nbcampauthenticationfilterexample.entity.UserRole;

public record UserRegisterRequest(
    String username,
    String password,
    String email,
    UserRole role
) {

}
