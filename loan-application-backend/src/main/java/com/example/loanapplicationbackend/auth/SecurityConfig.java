package com.example.loanapplicationbackend.auth;

import com.example.loanapplicationbackend.security.JwtAuthenticationEntryPoint;
import com.example.loanapplicationbackend.security.JwtAuthenticationFilter;
import com.example.loanapplicationbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private JwtAuthenticationEntryPoint point;
    private final UserService userService;
    private final JwtAuthenticationFilter filter;
    private final PasswordEncoder passwordEncoder;
/*
    public SecurityConfig(JwtAuthenticationEntryPoint point, JwtAuthenticationFilter filter) {
        this.point = point;
        this.filter = filter;
    }*/

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
/*
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests((auth) -> {
                    auth.requestMatchers("/home/**").authenticated();
                    auth.requestMatchers("/rest/auth/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);


        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests((auth) -> {
                    auth.requestMatchers("/home/**").authenticated()
                    .requestMatchers("/rest/auth/**").permitAll()
                    .anyRequest().authenticated();
                })
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);*/

        http
                .csrf(AbstractHttpConfigurer::disable
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/home/**","/api/v1/signup", "/api/v1/signin").permitAll()
                        .requestMatchers(HttpMethod.GET, "/rest/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider()).addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
