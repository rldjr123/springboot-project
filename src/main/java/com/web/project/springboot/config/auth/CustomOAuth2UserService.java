package com.web.project.springboot.config.auth;

import com.web.project.springboot.config.auth.dto.OAuthAttributes;
import com.web.project.springboot.config.auth.dto.SessionUser;
import com.web.project.springboot.domain.user.User;
import com.web.project.springboot.domain.user.UserREpository;
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
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
    private final UserREpository userRepository;
    private final HttpSession httpSession;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        /*현재 로그인 진행중인 서비스를 구분하는 코드(구글,네이버,카카오등등)*/
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        /*OAuth2로그인진행시 키가 되는 필드값, 테이블 primary key와 같은거임, 기본적으로 구글은 지원하지만, 네이버 카카오은 기본지원하지않음, 구글의 기본코드는 sub*/
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();

        /*OAuth2Service를 통해가져온 OAuth2User의 attribute를 담을 클래스*/
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        /*세션에 사용자정보를 저장하기위한 Dto클래스, 기존 User 클래스와는 다름 새로생성*/
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey()))
                ,attributes.getAttributes()
                ,attributes.getNameAttributeKey());

    }


    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());


        return userRepository.save(user);
    }




}
