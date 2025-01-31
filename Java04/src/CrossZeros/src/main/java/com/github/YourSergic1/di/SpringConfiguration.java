package com.github.YourSergic1.di;

import com.github.YourSergic1.datasource.repository.GameContainer;
import com.github.YourSergic1.datasource.repository.GameRepository;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringConfiguration {

    @Bean
    @Scope("singleton")
    public GameContainer gameContainer() {
        return new GameContainer();
    }

    @Bean
    public GameRepository gameRepository(GameContainer gameContainer) {
        return new GameRepository(gameContainer);
    }

    @Bean
    public RepositoryServiceImpl repositoryService(GameRepository gameRepository) {
        return new RepositoryServiceImpl(gameRepository);
    }
}
