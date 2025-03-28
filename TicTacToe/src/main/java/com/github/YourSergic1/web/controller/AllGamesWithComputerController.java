package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.mapper.GameEntityMapper;
import com.github.YourSergic1.datasource.model.GameEntity;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Game;
import com.github.YourSergic1.domain.model.jwt.JwtProvider;
import com.github.YourSergic1.domain.service.GameService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AllGamesWithComputerController {

    RepositoryServiceImpl repositoryServiceImpl;
    GameService gameService;
    JwtProvider jwtProvider;

    @Autowired
    public AllGamesWithComputerController(RepositoryServiceImpl repositoryServiceImpl, GameService gameService, JwtProvider jwtProvider) {
        this.repositoryServiceImpl = repositoryServiceImpl;
        this.gameService = gameService;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    @GetMapping("/allGamesWithComputer")
    public String allGamesWithComputer(@CookieValue(name = "accessToken", required = false) String accessToken, Model model) {
        final Claims claims = jwtProvider.getClaims(accessToken);
        String login = claims.getSubject();
        List<GameEntity> allGames = repositoryServiceImpl.getAllGamesWithComputer(login);
        Map<GameEntity, Integer> gameStatuses = new HashMap<>();
        for (GameEntity gameEntity : allGames) {
            Game game = GameEntityMapper.EntityToGame(gameEntity);
            int status = gameService.isGameOver(game, login); // Получаем статус
            gameStatuses.put(gameEntity, status); // Добавляем в map
        }
        model.addAttribute("gameStatuses", gameStatuses);
        return "allGamesWithComputer";
    }


}
