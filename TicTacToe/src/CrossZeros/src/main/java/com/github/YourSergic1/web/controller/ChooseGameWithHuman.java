package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.mapper.GameEntityMapper;
import com.github.YourSergic1.datasource.mapper.StatisticEntityMapper;
import com.github.YourSergic1.datasource.mapper.UserEntityMapper;
import com.github.YourSergic1.datasource.model.GameEntity;
import com.github.YourSergic1.datasource.model.StatisticEntity;
import com.github.YourSergic1.datasource.model.UserEntity;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Game;
import com.github.YourSergic1.domain.model.Statistic;
import com.github.YourSergic1.domain.model.User;
import com.github.YourSergic1.domain.model.jwt.JwtProvider;
import com.github.YourSergic1.domain.service.GameService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ChooseGameWithHuman {

    RepositoryServiceImpl repositoryServiceImpl;
    GameService gameService;
    JwtProvider jwtProvider;

    @Autowired
    public ChooseGameWithHuman(RepositoryServiceImpl repositoryServiceImpl, GameService gameService, JwtProvider jwtProvider) {
        this.repositoryServiceImpl = repositoryServiceImpl;
        this.gameService = gameService;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    @GetMapping("/chooseGameWithHuman")
    public String chooseGameWithHuman(Model model, @CookieValue(name = "accessToken", required = false) String accessToken) {
        final Claims claims = jwtProvider.getClaims(accessToken);
        String userLogin = claims.getSubject();
        List<GameEntity> allGames = repositoryServiceImpl.getAllEmptyGamesWithHuman(userLogin);

        Map<GameEntity, String> allGamesWithLogin = new HashMap<>();
        for (GameEntity gameEntity : allGames) {
            Game game = GameEntityMapper.EntityToGame(gameEntity);
            String login = repositoryServiceImpl.getUser(game.getUser1()).get().getLogin();
            allGamesWithLogin.put(gameEntity, login); // Добавляем в map
        }
        model.addAttribute("allGamesWithLogin", allGamesWithLogin);
        return "chooseGameWithHuman";
    }

    @Transactional
    @PostMapping("/connectToTheGame")
    public String connectToTheGame(@RequestParam UUID gameId, @CookieValue(name = "accessToken", required = false) String accessToken) {
        final Claims claims = jwtProvider.getClaims(accessToken);
        String login = claims.getSubject();
        UUID userId = repositoryServiceImpl.getUser(login).get().getId();
        Optional<GameEntity> gameEntity = repositoryServiceImpl.getGame(gameId);
        if (gameEntity.isEmpty()) return "menu";
        UserEntity userEntity = repositoryServiceImpl.getUser(login).get();
        User user = UserEntityMapper.EntityToUser(userEntity);
        Game game = GameEntityMapper.EntityToGame(gameEntity.get());
        if (game.getUser2() == null) game.setUser2(user.getId());
        else return "menu";
        GameEntity newGameEntity = GameEntityMapper.GameToEntity(game);
        repositoryServiceImpl.saveGame(newGameEntity);
        Optional<StatisticEntity> statisticEntity = repositoryServiceImpl.getStatistic(userId);
        if (statisticEntity.isEmpty()) return "/menu";
        Statistic statistic = StatisticEntityMapper.EntityToStatistic(statisticEntity.get());
        statistic.setAllGamesCounter(statistic.getAllGamesCounter() + 1);
        repositoryServiceImpl.saveStatistic(StatisticEntityMapper.StatisticToEntity(statistic));
        return "redirect:/game/" + gameId;
    }

}
