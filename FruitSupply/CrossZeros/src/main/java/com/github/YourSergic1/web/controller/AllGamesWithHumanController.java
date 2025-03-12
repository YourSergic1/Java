package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.mapper.GameEntityMapper;
import com.github.YourSergic1.datasource.model.GameEntity;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Game;
import com.github.YourSergic1.domain.service.GameServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AllGamesWithHumanController {

    RepositoryServiceImpl repositoryServiceImpl;
    GameServiceImplementation gameServiceImplementation;

    @Autowired
    public AllGamesWithHumanController(RepositoryServiceImpl repositoryServiceImpl, GameServiceImplementation gameServiceImplementation) {
        this.repositoryServiceImpl = repositoryServiceImpl;
        this.gameServiceImplementation = gameServiceImplementation;
    }

    @Transactional
    @GetMapping("/allGamesWithHuman")
    public String allGamesWithHuman(Principal principal, Model model) {
        List<GameEntity> allGames = repositoryServiceImpl.getAllGamesWithHuman(principal);
        Map<GameEntity, Integer> gameStatuses = new HashMap<>();
        for (GameEntity gameEntity : allGames) {
            Game game = GameEntityMapper.EntityToGame(gameEntity);
            int status = gameServiceImplementation.isGameOver(game, principal); // Получаем статус
            gameStatuses.put(gameEntity, status); // Добавляем в map
        }
        model.addAttribute("gameStatuses", gameStatuses);
        return "allGamesWithHuman";
    }


}
