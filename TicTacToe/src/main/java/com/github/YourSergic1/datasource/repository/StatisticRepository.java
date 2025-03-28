package com.github.YourSergic1.datasource.repository;

import com.github.YourSergic1.datasource.model.StatisticEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Scope("singleton")
public interface StatisticRepository extends JpaRepository<StatisticEntity, UUID> {
    Optional<StatisticEntity> findByPlayerId(UUID playerId);

    @Query("SELECT s FROM StatisticEntity s ORDER BY s.winningCounter DESC LIMIT :limit")
    List<StatisticEntity> findTopByWinning(@Param("limit") int limit);

    void deleteByPlayerId(UUID playerId);
}
