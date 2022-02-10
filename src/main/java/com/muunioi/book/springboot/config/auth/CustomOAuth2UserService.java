package com.muunioi.book.springboot.config.auth;

import com.muunioi.book.springboot.config.auth.dto.OAuthAttributes;
import com.muunioi.book.springboot.config.auth.dto.SessionUser;
import com.muunioi.book.springboot.domain.user.User;
import com.muunioi.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
// 소셜 로그인 이후 가져온 사용자 정보를 기반으로 가입 및 정보수정, 세션 저장 등의 기능 지원
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); //현재 로그인 중인 서비스 구분(구글/네이버/카카오/..)

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName(); //로그인 진행 시 키가 되는 필드 값(PK 비슷)

        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        //OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user));
        //세션에 사용자 정보를 저장하기 위한 Dto 클래스-SessionUser (**User 클래스를 사용하지 않음?)

        return new DefaultOAuth2User(Collections.singleton(
                new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    //사용자 정보 저장 및 업데이트
    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
