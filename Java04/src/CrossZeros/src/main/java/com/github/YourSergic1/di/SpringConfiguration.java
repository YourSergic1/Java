package com.github.YourSergic1.di;

import com.github.YourSergic1.datasource.repository.FieldRepository;
import com.github.YourSergic1.datasource.repository.GameRepository;
import com.github.YourSergic1.datasource.repository.UserRepository;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.service.GameServiceImplementation;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class SpringConfiguration {
    @Bean
    public RepositoryServiceImpl repositoryService(GameRepository gameRepository, UserRepository userRepository, FieldRepository fieldRepository) {
        return new RepositoryServiceImpl(gameRepository, userRepository, fieldRepository);
    }
}
