package com.muunioi.book.springboot.config.auth;

import com.muunioi.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests() //URL별 권한 관리 설정 옵션 시작점
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() //전체열람권한
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // User에게만 권한
                    .anyRequest().authenticated() //그 외의 URL들 -> 인증된 사용자에게만(로그인된)
                .and()
                    .logout() //logout 설정 시작점
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login() //login 설정 시작점
                        .userInfoEndpoint() //로그인 성공 후 사용자 정보를 가져올 때의 설정 담당
                            .userService(customOAuth2UserService); //소셜로그인 성공 후속 조치를 진행 할 UserService 인터페이스의 구현체 등록
                                                                    // -> 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시 가능
    }
}
