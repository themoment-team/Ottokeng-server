package com.example.ottokeng.global.security;

import com.example.ottokeng.global.security.jwt.JwtExceptionFilter;
import com.example.ottokeng.global.security.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig{

    private final JwtTokenFilter jwtTokenFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf().disable()
                .httpBasic().disable();

        http.authorizeRequests()
                .antMatchers("/login/oauth/**").permitAll()
                .antMatchers("/token/reissue").permitAll()
                .antMatchers(HttpMethod.GET, "/post/comment/**").permitAll()
                .antMatchers( HttpMethod.GET,"/post/writing").permitAll()
                .antMatchers("/my-page/**").hasAuthority("ROLE_USER")
                .antMatchers("/").permitAll();

        http.authorizeRequests()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtExceptionFilter, JwtTokenFilter.class);

        return http.build();

    }

}
