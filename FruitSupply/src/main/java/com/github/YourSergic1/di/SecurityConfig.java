package com.github.YourSergic1.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home", "/login", "/addDelivery/*", "/chooseInfoForReport", "/chooseInfoForReport/report").permitAll()
                        .anyRequest().authenticated()
                ).formLogin(form -> form
                        .loginPage("/authentication")
                        .defaultSuccessUrl("/home")
                        .permitAll()
                        .failureUrl("/authentication?error=true")
                );

        return http.build();
    }
}
