package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.mapper.GameEntityMapper;
import com.github.YourSergic1.datasource.mapper.UserEntityMapper;
import com.github.YourSergic1.datasource.model.GameEntity;
import com.github.YourSergic1.datasource.model.UserEntity;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Game;
import com.github.YourSergic1.domain.model.User;
import com.github.YourSergic1.domain.service.GameServiceImplementation;
import com.github.YourSergic1.domain.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;

@Controller
public class ChooseGameWithHuman {

    private final UserServiceImplementation userServiceImplementation;
    RepositoryServiceImpl repositoryServiceImpl;
    GameServiceImplementation gameServiceImplementation;

    @Autowired
    public ChooseGameWithHuman(RepositoryServiceImpl repositoryServiceImpl, GameServiceImplementation gameServiceImplementation, UserServiceImplementation userServiceImplementation) {
        this.repositoryServiceImpl = repositoryServiceImpl;
        this.gameServiceImplementation = gameServiceImplementation;
        this.userServiceImplementation = userServiceImplementation;
    }

    @Transactional
    @GetMapping("/chooseGameWithHuman")
    public String chooseGameWithHuman(Model model, Principal principal) {
        List<GameEntity> allGames = repositoryServiceImpl.getAllEmptyGamesWithHuman(principal);


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
    public String connectToTheGame(@RequestParam UUID gameId, Principal principal) {
        Optional<GameEntity> gameEntity = repositoryServiceImpl.getGame(gameId);
        if (gameEntity.isEmpty()) return "menu";
        UserEntity userEntity = repositoryServiceImpl.getUser(principal.getName()).get();
        User user = UserEntityMapper.EntityToUser(userEntity);
        Game game = GameEntityMapper.EntityToGame(gameEntity.get());
        if (game.getUser2() == null) game.setUser2(user.getId());
        else return "menu";
        GameEntity newGameEntity = GameEntityMapper.GameToEntity(game);
        repositoryServiceImpl.saveGame(newGameEntity);
        return "redirect:/game/" + gameId;
    }

}
