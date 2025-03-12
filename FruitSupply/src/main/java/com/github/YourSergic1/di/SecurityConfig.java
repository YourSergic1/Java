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
                .csrf(AbstractHttpConfigurer::disable) // Отключаем CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home", "/login","/addDelivery/*","/report").permitAll() // Доступ к этим страницам без авторизации
                        .anyRequest().authenticated() // Все остальные страницы требуют аутентификацию
                ).formLogin(form -> form
                        .loginPage("/authentication") // Указываем страницу входа
                        .defaultSuccessUrl("/home")
                        .permitAll() // Разрешаем доступ к странице входа всем
                        .failureUrl("/authentication?error=true") // Перенаправление при ошибке входа
                );
        // Разрешаем всем пользователям выйти

        return http.build();
    }
}
