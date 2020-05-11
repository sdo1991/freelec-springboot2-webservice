package com.dongoh.book.springboot.config.auth;

import com.dongoh.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity//spring security 관련 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()//h2-console이용위해 해당옵션 disable
            .and().authorizeRequests()//url별 권한관리 시작, antmatchers쑤기위해 선언필요
            .antMatchers("/","/css/**","/image/**","/js/**","/h2-console/**").permitAll()
            .antMatchers("/api/v1/**").hasRole(Role.USER.name())
            .anyRequest().authenticated()//나머지url은 인증(로그인)된 사용자만 허용
            .and().logout().logoutSuccessUrl("/")
            .and()
                .oauth2Login()//로그인 기능 설정 시작
                   .userInfoEndpoint()//로그인 성공 후 사용자 정보 가져오기 설정
                        .userService(customOAuth2UserService);//로그인 성공 후 진행할 UserService 인터페이스구현체 등록
    }
}
