package com.github.YourSergic1.web.controller;

import org.springframework.ui.Model;
import com.github.YourSergic1.datasource.mapper.GameEntityMapper;
import com.github.YourSergic1.datasource.model.CurrentGameEntity;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.CurrentGame;
import com.github.YourSergic1.domain.service.GameServiceImplementation;
import com.github.YourSergic1.web.mapper.GameDtoMapper;
import com.github.YourSergic1.web.model.CurrentGameDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class GameController {
    GameServiceImplementation gameServiceImplementation;
    RepositoryServiceImpl repositoryServiceImpl;

    public GameController(GameServiceImplementation gameServiceImplementation, RepositoryServiceImpl repositoryServiceImpl) {
        this.gameServiceImplementation = gameServiceImplementation;
        this.repositoryServiceImpl = repositoryServiceImpl;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping
    public String startGame() {
        CurrentGame currentGame = new CurrentGame();
        CurrentGameDto currentGameDto = GameDtoMapper.toDto(currentGame);
        repositoryServiceImpl.saveGame(GameEntityMapper.GameToEntity(currentGame));
        return "redirect:/game/" + currentGameDto.getId();
    }

    @GetMapping("/game/{gameId}")
    public String showGame(@PathVariable UUID gameId, Model model) {
        CurrentGameEntity currentGameEntity = repositoryServiceImpl.getGame(gameId);
        CurrentGame currentGame = GameEntityMapper.EntityToGame(currentGameEntity);
        CurrentGameDto currentGameDto = GameDtoMapper.toDto(currentGame);
        model.addAttribute("gameBoard", currentGameDto.getField().getField());
        if (gameServiceImplementation.isGameOver(currentGame.getField().getBoard()) == 1)
            model.addAttribute("inGame", true);
        else {
            model.addAttribute("inGame", false);
            if (gameServiceImplementation.isGameOver(currentGame.getField().getBoard()) == -10) {
                model.addAttribute("isWin", 1);
            } else if (gameServiceImplementation.isGameOver(currentGame.getField().getBoard()) == 10) {
                model.addAttribute("isWin", -1);
            } else if (gameServiceImplementation.isGameOver(currentGame.getField().getBoard()) == 0) {
                model.addAttribute("isWin", 0);
            }
        }
        return "game";
    }

    @PostMapping("/game/{gameId}")
    public String makeStep(@PathVariable UUID gameId, @RequestParam int num) {
        CurrentGameEntity currentGameEntity = repositoryServiceImpl.getGame(gameId);
        CurrentGame currentGame = GameEntityMapper.EntityToGame(currentGameEntity);
        if (gameServiceImplementation.isGameOver(currentGame.getField().getBoard()) == 1) {
            if (gameServiceImplementation.validateField(currentGame, num / 3, num % 3)) {
                currentGame.setPlayer(num / 3, num % 3);
                repositoryServiceImpl.saveGame(GameEntityMapper.GameToEntity(currentGame));
                if (gameServiceImplementation.isGameOver(currentGame.getField().getBoard()) == 1) {
                    currentGame.setComputer(gameServiceImplementation.nextMove(currentGame)[0], gameServiceImplementation.nextMove(currentGame)[1]);
                    repositoryServiceImpl.saveGame(GameEntityMapper.GameToEntity(currentGame));
                }
            }
        }
        return "redirect:/game/" + gameId;
    }


}
