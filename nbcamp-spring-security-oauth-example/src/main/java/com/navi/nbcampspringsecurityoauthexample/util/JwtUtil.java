package com.navi.nbcampspringsecurityoauthexample.util;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateAccessToken(Long memberId) {
        return "SAMPLE_ACCESS_TOKEN-" + memberId;
    }

}
