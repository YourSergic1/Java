package com.github.YourSergic1.domain.service;

import com.github.YourSergic1.datasource.mapper.StatisticEntityMapper;
import com.github.YourSergic1.datasource.mapper.UserEntityMapper;
import com.github.YourSergic1.datasource.model.StatisticEntity;
import com.github.YourSergic1.datasource.model.UserEntity;
import com.github.YourSergic1.datasource.repository.GameRepository;
import com.github.YourSergic1.datasource.repository.StatisticRepository;
import com.github.YourSergic1.datasource.repository.UserRepository;
import com.github.YourSergic1.domain.model.SignUpRequest;
import com.github.YourSergic1.domain.model.Statistic;
import com.github.YourSergic1.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final StatisticRepository statisticRepository;

    @Autowired
    UserService(UserRepository userRepository, GameRepository gameRepository, StatisticRepository statisticRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.statisticRepository = statisticRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .map(user -> org.springframework.security.core.userdetails.User.builder()
                        .username(user.getLogin())
                        .password(user.getPassword())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public void registerUser(SignUpRequest signUpRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = signUpRequest.getPassword();
        password = encoder.encode(password);
        User user = new User(signUpRequest.getLogin(), password);
        Statistic statistic = new Statistic(user.getId());
        UserEntity userEntity = UserEntityMapper.UserToEntity(user);
        StatisticEntity statisticEntity = StatisticEntityMapper.StatisticToEntity(statistic);
        Optional<UserEntity> existingUser = userRepository.findByLogin(userEntity.getLogin());
        if (existingUser.isPresent()) {
        } else {
            userRepository.save(userEntity);
            statisticRepository.save(statisticEntity);
        }
    }

    public void deleteUser(UUID id) {
        Optional<UserEntity> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.deleteById(id);
            gameRepository.deleteGamesByUserId(id);
        }
    }
}
