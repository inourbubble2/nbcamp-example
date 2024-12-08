package com.navi.nbcampspringsecurityoauthexample.oauth;

import com.navi.nbcampspringsecurityoauthexample.member.Member;
import com.navi.nbcampspringsecurityoauthexample.member.MemberRepository;
import com.navi.nbcampspringsecurityoauthexample.oauth.model.OAuthUser;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuthUserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User loadedUser = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = loadedUser.getAttribute("id").toString();
        String nickname = ((Map) loadedUser.getAttribute("properties")).get("nickname").toString();

        Member member = memberRepository.findByProviderAndProviderId(provider, providerId)
            .orElseGet(() -> {
                log.info("신규 회원 가입 - provider:{} providerId:{} nickname:{}", provider, providerId, nickname);
                return memberRepository.save(new Member(provider, providerId, nickname));
            });

        return new OAuthUser(member.getId(), member.getProvider(), member.getProviderId(), member.getNickname());
    }
}
