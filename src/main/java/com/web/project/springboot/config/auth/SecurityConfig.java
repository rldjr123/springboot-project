package com.web.project.springboot.config.auth;

import com.web.project.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity /*Security 설정 활성화*/
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        /*h2-console 화면을 사용하기위해 옵션 disable*/
        http.csrf().disable().headers().frameOptions().disable().and()
                
                /*URL별 권한관리를 설정하는 옵션의 시작점, 이게선언되야만 antMatchers옵션을 사용가능*/
                .authorizeRequests()
                
                /*antMatchers - 권한대상관리지정옵션, URL, HTTP메소드별로 관리가가능*/
                .antMatchers("/","/css/**","/images/**","/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                
                /*설정된값들이외 나머지 url들을 나타냄,여기서는 authenticated을 추가하여 나머지 url들은 인증된 사용자만 허용하게함, 즉 로그인한사람 */
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()

                /*oauth2 로그인기능에 대한 여러설정의 진입점*/
                .oauth2Login()

                /*oaUTH2 로그인성공후 사용자 정보를 가져올때의 설정들을 담당*/
                .userInfoEndpoint()

                /*소셜로그인 성공시 후속조치를 진행할 Service를 등록, 리소스서버(즉 소셜서비스들)에서 사용자정보를 가져온상태에서 추가로 진행하고자하는 기능*/
                .userService(customOAuth2UserService);

    }

}
