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
public class EndedGamesController {
    RepositoryServiceImpl repositoryServiceImpl;
    GameService gameService;
    JwtProvider jwtProvider;

    @Autowired
    public EndedGamesController(RepositoryServiceImpl repositoryServiceImpl, GameService gameService, JwtProvider jwtProvider) {
        this.repositoryServiceImpl = repositoryServiceImpl;
        this.gameService = gameService;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    @GetMapping("/allEndedGames")
    public String allGamesWithComputer(@CookieValue(name = "accessToken", required = false) String accessToken, Model model) {
        final Claims claims = jwtProvider.getClaims(accessToken);
        String login = claims.getSubject();
        List<GameEntity> allGames = repositoryServiceImpl.getAllGamesWithComputer(login);
        allGames.addAll(repositoryServiceImpl.getAllGamesWithHuman(login));
        allGames = allGames.stream().filter(gameEntity -> (gameService.isGameOver(GameEntityMapper.EntityToGame(gameEntity), login) == 10 || gameService.isGameOver(GameEntityMapper.EntityToGame(gameEntity), login) == -10 || gameService.isGameOver(GameEntityMapper.EntityToGame(gameEntity), login) == 0)).toList();
        Map<Game, Integer> statuses = new HashMap<>();
        for (GameEntity gameEntity : allGames) {
            Game game = GameEntityMapper.EntityToGame(gameEntity);
            int status = gameService.isGameOver(GameEntityMapper.EntityToGame(gameEntity), login);
            statuses.put(game, status);
        }
        model.addAttribute("statuses", statuses);
        return "allEndedGames";
    }

}
