package com.github.YourSergic1.di;

import com.github.YourSergic1.domain.model.jwt.JwtAuthentication;
import com.github.YourSergic1.domain.model.jwt.JwtProvider;
import com.github.YourSergic1.domain.model.jwt.JwtResponse;
import com.github.YourSergic1.domain.service.AuthorizationService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import com.github.YourSergic1.domain.model.jwt.JwtUtil;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFilter extends GenericFilterBean {

    private static final String ACCESS_TOKEN_COOKIE = "accessToken";
    private static final String REFRESH_TOKEN_COOKIE = "refreshToken";
    private final JwtProvider jwtProvider;
    @Autowired
    private final AuthorizationService authorizationService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse; // Добавлено

        final String accessToken = getTokenFromCookies(request, ACCESS_TOKEN_COOKIE);
        final String refreshToken = getTokenFromCookies(request, REFRESH_TOKEN_COOKIE);

        if (accessToken != null && jwtProvider.validateAccessToken(accessToken)) {
            final Claims claims = jwtProvider.getClaims(accessToken);
            final JwtAuthentication jwtInfoToken = JwtUtil.createJwtAuthentication(claims);
            jwtInfoToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtInfoToken);
        } else if (refreshToken != null && jwtProvider.validateRefreshToken(refreshToken)) {
            JwtResponse newTokens = authorizationService.updateAccessToken(refreshToken);
            if (newTokens.getAccessToken() != null) {
                setCookie(response, ACCESS_TOKEN_COOKIE, newTokens.getAccessToken(), 900); // 15 минут
                final Claims claims = jwtProvider.getClaims(newTokens.getAccessToken());
                final JwtAuthentication jwtInfoToken = JwtUtil.createJwtAuthentication(claims);
                jwtInfoToken.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(jwtInfoToken);
            } else {
                deleteCookies(response);
            }
        } else {
            deleteCookies(response);
        }
        chain.doFilter(request, response);
    }

    private String getTokenFromCookies(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    private void deleteCookies(HttpServletResponse response) {
        setCookie(response, ACCESS_TOKEN_COOKIE, null, 0);
        setCookie(response, REFRESH_TOKEN_COOKIE, null, 0);
    }
}