package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.mapper.StatisticEntityMapper;
import com.github.YourSergic1.datasource.mapper.UserEntityMapper;
import com.github.YourSergic1.datasource.model.StatisticEntity;
import com.github.YourSergic1.datasource.model.UserEntity;
import com.github.YourSergic1.domain.model.Statistic;
import com.github.YourSergic1.domain.model.User;
import com.github.YourSergic1.domain.model.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import com.github.YourSergic1.datasource.mapper.GameEntityMapper;
import com.github.YourSergic1.datasource.model.GameEntity;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Game;
import com.github.YourSergic1.domain.service.GameService;
import com.github.YourSergic1.web.mapper.GameDtoMapper;
import com.github.YourSergic1.web.model.GameDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
public class GameController {
    GameService gameService;
    RepositoryServiceImpl repositoryServiceImpl;
    JwtProvider jwtProvider;

    @Autowired
    public GameController(GameService gameService, RepositoryServiceImpl repositoryServiceImpl, JwtProvider jwtProvider) {
        this.gameService = gameService;
        this.repositoryServiceImpl = repositoryServiceImpl;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    @GetMapping("/game/{gameId}")
    public String showGame(@PathVariable UUID gameId, Model model, @CookieValue(name = "accessToken", required = false) String accessToken) {
        final Claims claims = jwtProvider.getClaims(accessToken);
        String login = claims.getSubject();
        UUID userId = UUID.fromString(claims.get("id").toString());
        Optional<GameEntity> optionalGame = repositoryServiceImpl.getGame(gameId, userId);
        if (optionalGame.isEmpty()) return "menu";
        Game game = GameEntityMapper.EntityToGame(optionalGame.get());
        GameDto gameDto = GameDtoMapper.toDto(game);
        model.addAttribute("gameBoard", gameDto.getField().getBoard());
        if (gameService.isGameOver(game, login) == 1)
            model.addAttribute("inGame", true);
        else {
            model.addAttribute("inGame", false);
            if (gameService.isGameOver(game, login) == -10) {
                model.addAttribute("isWin", 1);
            } else if (gameService.isGameOver(game, login) == 10) {
                model.addAttribute("isWin", -1);
            } else if (gameService.isGameOver(game, login) == 0) {
                model.addAttribute("isWin", 0);
            }
        }
        if (game.getUser2() == null) model.addAttribute("waitingForEnemy", true);
        else model.addAttribute("waitingForEnemy", false);
        model.addAttribute("playerFigure", gameService.playerFigure(game, login));
        model.addAttribute("human", game.isGameWithHuman());
        model.addAttribute("isPlayerTurn", gameService.isPlayerTurn(game, login));
        return "game";
    }

    @Transactional
    @PostMapping("/game/{gameId}")
    public String makeStep(@PathVariable UUID gameId, @RequestParam int num, @CookieValue(name = "accessToken", required = false) String accessToken) {
        final Claims claims = jwtProvider.getClaims(accessToken);
        String login = claims.getSubject();
        UUID userId = UUID.fromString(claims.get("id").toString());
        Optional<GameEntity> optionalGame = repositoryServiceImpl.getGame(gameId, userId);
        if (optionalGame.isEmpty()) return "menu";
        Optional<StatisticEntity> optionalStatistic = repositoryServiceImpl.getStatistic(userId);
        if (optionalStatistic.isEmpty()) return "menu";
        Game game = GameEntityMapper.EntityToGame(optionalGame.get());
        Statistic statistic = StatisticEntityMapper.EntityToStatistic(optionalStatistic.get());
        if (!game.isGameWithHuman()) {
            if (game.isInGame()) {
                if (gameService.validateField(game, num / 3, num % 3)) {
                    game.setPlayer1(num / 3, num % 3);
                    if (gameService.isGameOver(game, login) == 10 || gameService.isGameOver(game, login) == -10 || gameService.isGameOver(game, login) == 0) {
                        changeStatistic(game, login, statistic);
                    }
                    if (game.isInGame()) {
                        game.setPlayer2(gameService.nextMove(game)[0], gameService.nextMove(game)[1]);
                        if (gameService.isGameOver(game, login) == 10 || gameService.isGameOver(game, login) == -10 || gameService.isGameOver(game, login) == 0) {
                            changeStatistic(game, login, statistic);
                        }
                    }
                    repositoryServiceImpl.saveGame(GameEntityMapper.GameToEntity(game));
                    repositoryServiceImpl.saveStatistic(StatisticEntityMapper.StatisticToEntity(statistic));
                }
            }
        } else {
            if (game.isInGame()) {
                Optional<UserEntity> userEntity = repositoryServiceImpl.getUser(login);
                if (userEntity.isEmpty()) {
                    return "menu";
                }
                User user = UserEntityMapper.EntityToUser(userEntity.get());
                int currentUserFigure = 0;
                if (game.getPlayer1Figure() == 1 && user.getId().equals(game.getUser1())) {
                    currentUserFigure = 1;
                } else if (game.getPlayer2Figure() == 1 && user.getId().equals(game.getUser2())) {
                    currentUserFigure = 1;
                }
                if (currentUserFigure == gameService.whoseTurn(game)) {
                    if (gameService.validateField(game, num / 3, num % 3)) {
                        if (user.getId().equals(game.getUser1())) game.setPlayer1(num / 3, num % 3);
                        else game.setPlayer2(num / 3, num % 3);
                        if (gameService.isGameOver(game, login) == 10 || gameService.isGameOver(game, login) == -10 || gameService.isGameOver(game, login) == 0) {
                            UUID player2 = game.getUser2();
                            if (userId.equals(player2)) player2 = game.getUser1();
                            Optional<StatisticEntity> statisticOfPlayer2 = repositoryServiceImpl.getStatistic(player2);
                            if (statisticOfPlayer2.isEmpty()) {
                                return "/menu";
                            }
                            Statistic statistic2 = StatisticEntityMapper.EntityToStatistic(statisticOfPlayer2.get());
                            changeStatisticForBothPlayers(game, login, statistic, statistic2);
                            repositoryServiceImpl.saveStatistic(StatisticEntityMapper.StatisticToEntity(statistic));
                            repositoryServiceImpl.saveStatistic(StatisticEntityMapper.StatisticToEntity(statistic2));
                        }
                        repositoryServiceImpl.saveGame(GameEntityMapper.GameToEntity(game));
                    }
                }
            }
        }
        return "redirect:/game/" + gameId;
    }

    public void changeStatistic(Game game, String login, Statistic statistic) {
        game.setInGame(false);
        if (gameService.isGameOver(game, login) == 10) {
            statistic.setLostCounter(statistic.getLostCounter() + 1);
        }
        if (gameService.isGameOver(game, login) == -10) {
            statistic.setWinningCounter(statistic.getWinningCounter() + 1);
        }
        if (gameService.isGameOver(game, login) == 0) {
            statistic.setDrawsCounter(statistic.getDrawsCounter() + 1);
        }
    }

    public void changeStatisticForBothPlayers(Game game, String login, Statistic statistic1, Statistic statistic2) {
        game.setInGame(false);
        if (gameService.isGameOver(game, login) == 10) {
            statistic1.setLostCounter(statistic1.getLostCounter() + 1);
            statistic2.setWinningCounter(statistic2.getWinningCounter() + 1);
        }
        if (gameService.isGameOver(game, login) == -10) {
            statistic1.setWinningCounter(statistic1.getWinningCounter() + 1);
            statistic2.setLostCounter(statistic2.getLostCounter() + 1);
        }
        if (gameService.isGameOver(game, login) == 0) {
            statistic1.setDrawsCounter(statistic1.getDrawsCounter() + 1);
            statistic2.setDrawsCounter(statistic2.getDrawsCounter() + 1);
        }
    }

}
