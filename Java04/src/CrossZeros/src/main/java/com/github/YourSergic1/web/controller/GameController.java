package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.mapper.UserEntityMapper;
import com.github.YourSergic1.datasource.model.UserEntity;
import com.github.YourSergic1.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import com.github.YourSergic1.datasource.mapper.GameEntityMapper;
import com.github.YourSergic1.datasource.model.GameEntity;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Game;
import com.github.YourSergic1.domain.service.GameServiceImplementation;
import com.github.YourSergic1.web.mapper.GameDtoMapper;
import com.github.YourSergic1.web.model.GameDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Controller
public class GameController {
    GameServiceImplementation gameServiceImplementation;
    RepositoryServiceImpl repositoryServiceImpl;

    @Autowired
    public GameController(GameServiceImplementation gameServiceImplementation, RepositoryServiceImpl repositoryServiceImpl) {
        this.gameServiceImplementation = gameServiceImplementation;
        this.repositoryServiceImpl = repositoryServiceImpl;
    }

    @Transactional
    @GetMapping("/game/{gameId}")
    public String showGame(@PathVariable UUID gameId, Model model, Principal principal) {
        Optional<GameEntity> optionalGame = repositoryServiceImpl.getGame(gameId, principal);
        if (optionalGame.isEmpty()) return "menu";
        Game game = GameEntityMapper.EntityToGame(optionalGame.get());
        GameDto gameDto = GameDtoMapper.toDto(game);
        model.addAttribute("gameBoard", gameDto.getField().getBoard());
        if (gameServiceImplementation.isGameOver(game, principal) == 1)
            model.addAttribute("inGame", true);
        else {
            model.addAttribute("inGame", false);
            if (gameServiceImplementation.isGameOver(game, principal) == -10) {
                model.addAttribute("isWin", 1);
            } else if (gameServiceImplementation.isGameOver(game, principal) == 10) {
                model.addAttribute("isWin", -1);
            } else if (gameServiceImplementation.isGameOver(game, principal) == 0) {
                model.addAttribute("isWin", 0);
            }
        }
        if (game.getUser2() == null) model.addAttribute("waitingForEnemy", true);
        else model.addAttribute("waitingForEnemy", false);
        model.addAttribute("playerFigure", gameServiceImplementation.playerFigure(game, principal));
        model.addAttribute("human", game.isGameWithHuman());
        model.addAttribute("isPlayerTurn", gameServiceImplementation.isPlayerTurn(game, principal));
        return "game";
    }

    @Transactional
    @PostMapping("/game/{gameId}")
    public String makeStep(@PathVariable UUID gameId, @RequestParam int num, Principal principal) {
        Optional<GameEntity> optionalGame = repositoryServiceImpl.getGame(gameId, principal);
        if (optionalGame.isEmpty()) return "menu";
        Game game = GameEntityMapper.EntityToGame(optionalGame.get());
        if (!game.isGameWithHuman()) {
            if (gameServiceImplementation.isGameOver(game, principal) == 1) {
                if (gameServiceImplementation.validateField(game, num / 3, num % 3)) {
                    game.setPlayer1(num / 3, num % 3);
                    repositoryServiceImpl.saveGame(GameEntityMapper.GameToEntity(game));
                    if (gameServiceImplementation.isGameOver(game, principal) == 1) {
                        game.setPlayer2(gameServiceImplementation.nextMove(game)[0], gameServiceImplementation.nextMove(game)[1]);
                        repositoryServiceImpl.saveGame(GameEntityMapper.GameToEntity(game));
                    }
                }
            }
        } else {
            if (gameServiceImplementation.isGameOver(game, principal) == 1) {
                Optional<UserEntity> userEntity = repositoryServiceImpl.getUser(principal.getName());
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
                if (currentUserFigure == gameServiceImplementation.whoseTurn(game)) {
                    if (gameServiceImplementation.validateField(game, num / 3, num % 3)) {
                        if (user.getId().equals(game.getUser1())) game.setPlayer1(num / 3, num % 3);
                        else game.setPlayer2(num / 3, num % 3);
                        repositoryServiceImpl.saveGame(GameEntityMapper.GameToEntity(game));
                    }
                }
            }
        }
        return "redirect:/game/" + gameId;
    }

}
