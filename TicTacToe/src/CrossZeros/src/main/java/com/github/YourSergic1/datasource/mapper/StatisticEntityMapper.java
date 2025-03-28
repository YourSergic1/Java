package com.github.YourSergic1.datasource.mapper;

import com.github.YourSergic1.datasource.model.StatisticEntity;
import com.github.YourSergic1.domain.model.Statistic;

public class StatisticEntityMapper {
    public static StatisticEntity StatisticToEntity(Statistic statistic) {
        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setUuid(statistic.getUuid());
        statisticEntity.setPlayerId(statistic.getPlayerId());
        statisticEntity.setAllGamesCounter(statistic.getAllGamesCounter());
        statisticEntity.setWinningCounter(statistic.getWinningCounter());
        statisticEntity.setLostCounter(statistic.getLostCounter());
        statisticEntity.setDrawsCounter(statistic.getDrawsCounter());
        return statisticEntity;
    }

    public static Statistic EntityToStatistic(StatisticEntity statisticEntity) {
        return new Statistic(statisticEntity.getUuid(),
                statisticEntity.getPlayerId(),
                statisticEntity.getAllGamesCounter(),
                statisticEntity.getWinningCounter(),
                statisticEntity.getLostCounter(),
                statisticEntity.getDrawsCounter());
    }
}
