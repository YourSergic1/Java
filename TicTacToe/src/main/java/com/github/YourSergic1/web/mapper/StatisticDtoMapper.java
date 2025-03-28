package com.github.YourSergic1.web.mapper;

import com.github.YourSergic1.domain.model.Statistic;
import com.github.YourSergic1.web.model.StatisticDto;

public class StatisticDtoMapper {
    public static StatisticDto statisticToDto(Statistic statistic) {
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setUuid(statistic.getUuid());
        statisticDto.setPlayerId(statistic.getPlayerId());
        statisticDto.setAllGamesCounter(statistic.getAllGamesCounter());
        statisticDto.setWinningCounter(statistic.getWinningCounter());
        statisticDto.setLostCounter(statistic.getLostCounter());
        statisticDto.setDrawsCounter(statistic.getDrawsCounter());
        return statisticDto;
    }

    public static Statistic dtoToStatistic(StatisticDto statisticDto) {
        return new Statistic(statisticDto.getUuid(),
                statisticDto.getPlayerId(),
                statisticDto.getAllGamesCounter(),
                statisticDto.getWinningCounter(),
                statisticDto.getLostCounter(),
                statisticDto.getDrawsCounter());
    }
}
