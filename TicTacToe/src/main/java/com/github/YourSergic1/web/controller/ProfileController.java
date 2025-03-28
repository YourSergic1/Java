package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.mapper.StatisticEntityMapper;
import com.github.YourSergic1.datasource.model.StatisticEntity;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.jwt.JwtProvider;
import com.github.YourSergic1.domain.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ProfileController {
    private final RepositoryServiceImpl repositoryService;
    private final UserService userService;
    JwtProvider jwtProvider;

    @Autowired
    ProfileController(RepositoryServiceImpl repositoryService, UserService userService, JwtProvider jwtProvider) {
        this.repositoryService = repositoryService;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/profile")
    public String profile(@CookieValue(name = "accessToken", required = false) String accessToken, Model model) {
        final Claims claims = jwtProvider.getClaims(accessToken);
        UUID userId = UUID.fromString(claims.get("id").toString());
        String login = claims.getSubject();
        Optional<StatisticEntity> statisticEntity = repositoryService.getStatistic(userId);
        if (statisticEntity.isEmpty()) return "menu";
        model.addAttribute("static", StatisticEntityMapper.EntityToStatistic(statisticEntity.get()));
        model.addAttribute("login", login);
        return "profile";
    }

    @PostMapping("/delete")
    public String delete(@CookieValue(name = "accessToken", required = false) String accessToken,
                         HttpServletResponse response) {
        final Claims claims = jwtProvider.getClaims(accessToken);
        String login = claims.getSubject();
        UUID id = repositoryService.getUser(login).get().getId();
        repositoryService.deleteStatisticByUserId(id);
        userService.deleteUser(id);
        clearAuthCookies(response);
        return "redirect:/menu";
    }

    @PostMapping("/exit")
    public String logout(HttpServletResponse response) {
        clearAuthCookies(response);
        return "redirect:/menu";
    }

    private void clearAuthCookies(HttpServletResponse response) {
        Cookie accessTokenCookie = new Cookie("accessToken", null);
        Cookie refreshTokenCookie = new Cookie("refreshToken", null);

        Arrays.asList(accessTokenCookie, refreshTokenCookie).forEach(cookie -> {
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0); // Немедленное удаление
            cookie.setSecure(true); // Для HTTPS
            response.addCookie(cookie);
        });
    }
}
