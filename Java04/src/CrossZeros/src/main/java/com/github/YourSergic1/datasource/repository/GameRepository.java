package com.github.YourSergic1.datasource.repository;

import com.github.YourSergic1.datasource.model.GameEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Scope("singleton")
public interface GameRepository extends JpaRepository<GameEntity, UUID> {
    List<GameEntity> findAllByUser1(UUID mainUserId);

    List<GameEntity> findAllByUser2(UUID mainUserId);

    List<GameEntity> findAllByGameWithHumanIsTrue();

    @Transactional
    @Modifying
    @Query("DELETE FROM GameEntity g WHERE g.user1 = :userId OR g.user2 = :userId")
    void deleteGamesByUserId(@Param("userId") UUID userId);
}