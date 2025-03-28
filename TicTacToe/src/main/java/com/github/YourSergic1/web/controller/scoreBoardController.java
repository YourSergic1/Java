package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.mapper.StatisticEntityMapper;
import com.github.YourSergic1.datasource.model.StatisticEntity;
import com.github.YourSergic1.datasource.model.UserEntity;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Statistic;
import com.github.YourSergic1.domain.model.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
public class scoreBoardController {
    private final RepositoryServiceImpl repositoryService;
    JwtProvider jwtProvider;

    @Autowired
    scoreBoardController(RepositoryServiceImpl repositoryService, JwtProvider jwtProvider) {
        this.repositoryService = repositoryService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/scoreBoard")
    public String profile(Model model, @RequestParam(defaultValue = "5") int limit) {
        List<StatisticEntity> allStatics = repositoryService.getAllStaticsByLimit(limit);
        Map<Statistic, String> statisticAndLogin = new LinkedHashMap<>();
        allStatics.forEach(statisticEntity -> {
            UserEntity userEntity = repositoryService.getUser(statisticEntity.getPlayerId()).get();
            statisticAndLogin.put(StatisticEntityMapper.EntityToStatistic(statisticEntity), userEntity.getLogin());
        });
        model.addAttribute("limit", limit);
        model.addAttribute("statisticAndLogin", statisticAndLogin);
        return "scoreBoard";
    }
}
