package com.navi.nbcampkotlincrudpractice.api.model.member

data class RegisterMemberRequest(
    val username: String,
    val password: String,
    val email: String
)
