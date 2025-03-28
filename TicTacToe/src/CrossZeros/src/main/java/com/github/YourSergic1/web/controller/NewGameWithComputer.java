package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.mapper.GameEntityMapper;
import com.github.YourSergic1.datasource.mapper.StatisticEntityMapper;
import com.github.YourSergic1.datasource.model.GameEntity;
import com.github.YourSergic1.datasource.model.StatisticEntity;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Game;
import com.github.YourSergic1.domain.model.Statistic;
import com.github.YourSergic1.domain.model.jwt.JwtProvider;
import com.github.YourSergic1.domain.service.GameService;
import com.github.YourSergic1.domain.service.UserService;
import com.github.YourSergic1.web.mapper.GameDtoMapper;
import com.github.YourSergic1.web.model.GameDto;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
public class NewGameWithComputer {
    RepositoryServiceImpl repositoryServiceImpl;
    GameService gameServiceImpl;
    UserService userServiceImpl;
    JwtProvider jwtProvider;

    @Autowired
    public NewGameWithComputer(RepositoryServiceImpl repositoryServiceImpl, GameService gameServiceImpl, UserService userServiceImpl, JwtProvider jwtProvider) {
        this.repositoryServiceImpl = repositoryServiceImpl;
        this.gameServiceImpl = gameServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/newGameWithComputer")
    public String newGameWithComputer() {
        return "newGameWithComputer";
    }

    @PostMapping("/startGameWithComputer")
    public String createGameComputer(@RequestParam int num, @CookieValue(name = "accessToken", required = false) String accessToken) {
        final Claims claims = jwtProvider.getClaims(accessToken);
        UUID userId = UUID.fromString(claims.get("id").toString());
        Game game;
        if (num == 1)
            game = new Game(0, 1, userId, null, false);
        else {
            game = new Game(1, 0, userId, null, false);
            gameServiceImpl.nextMove(game);
            game.setPlayer2(gameServiceImpl.nextMove(game)[0], gameServiceImpl.nextMove(game)[1]);
        }
        GameEntity gameEntity = GameEntityMapper.GameToEntity(game);
        repositoryServiceImpl.saveGame(gameEntity);
        Optional<StatisticEntity> statisticEntity = repositoryServiceImpl.getStatistic(userId);
        if (statisticEntity.isEmpty()) return "/menu";
        Statistic statistic = StatisticEntityMapper.EntityToStatistic(statisticEntity.get());
        statistic.setAllGamesCounter(statistic.getAllGamesCounter() + 1);
        repositoryServiceImpl.saveStatistic(StatisticEntityMapper.StatisticToEntity(statistic));
        GameDto gameDto = GameDtoMapper.toDto(game);
        return "redirect:/game/" + gameDto.getId();
    }
}
