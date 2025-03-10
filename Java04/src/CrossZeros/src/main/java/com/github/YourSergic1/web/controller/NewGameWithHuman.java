package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.mapper.GameEntityMapper;
import com.github.YourSergic1.datasource.model.GameEntity;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Game;
import com.github.YourSergic1.domain.service.GameServiceImplementation;
import com.github.YourSergic1.domain.service.UserServiceImplementation;
import com.github.YourSergic1.web.mapper.GameDtoMapper;
import com.github.YourSergic1.web.model.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class NewGameWithHuman {
    RepositoryServiceImpl repositoryServiceImpl;
    GameServiceImplementation gameServiceImpl;
    UserServiceImplementation userServiceImpl;

    @Autowired
    public NewGameWithHuman(RepositoryServiceImpl repositoryServiceImpl, GameServiceImplementation gameServiceImpl, UserServiceImplementation userServiceImpl) {
        this.repositoryServiceImpl = repositoryServiceImpl;
        this.gameServiceImpl = gameServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/newGameWithHuman")
    public String newGameWithHuman() {
        return "newGameWithHuman";
    }

    @PostMapping("/startGameWithHuman")
    public String createGameWithHuman(@RequestParam int num, Principal principal) {
        Game game;
        if (num == 1)
            game = new Game(0, 1, repositoryServiceImpl.getUser(principal.getName()).get().getId(), null, true);
        else game = new Game(1, 0, repositoryServiceImpl.getUser(principal.getName()).get().getId(), null, true);
        GameEntity gameEntity = GameEntityMapper.GameToEntity(game);
        repositoryServiceImpl.saveGame(gameEntity);
        GameDto gameDto = GameDtoMapper.toDto(game);
        return "redirect:/game/" + gameDto.getId();
    }
}
