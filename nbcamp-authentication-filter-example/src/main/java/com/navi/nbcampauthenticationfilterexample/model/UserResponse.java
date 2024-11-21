package com.navi.nbcampauthenticationfilterexample.model;

import com.navi.nbcampauthenticationfilterexample.entity.UserRole;

public record UserResponse(
    Long userId,
    String username,
    String email,
    UserRole role
) {
}
