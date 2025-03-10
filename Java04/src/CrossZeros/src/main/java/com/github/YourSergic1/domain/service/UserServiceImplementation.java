package com.github.YourSergic1.domain.service;

import com.github.YourSergic1.datasource.mapper.UserEntityMapper;
import com.github.YourSergic1.datasource.model.UserEntity;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.SignUpRequest;
import com.github.YourSergic1.domain.model.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {
    private final RepositoryServiceImpl repositoryServiceImpl;

    @Autowired
    UserServiceImplementation(RepositoryServiceImpl repositoryService) {
        this.repositoryServiceImpl = repositoryService;
    }

    @Override
    public User createUser(SignUpRequest signUpRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = signUpRequest.getPassword();
        password = encoder.encode(password);
        return new User(signUpRequest.getLogin(), password);
    }

    @Override
    public boolean registerUser(SignUpRequest signUpRequest) {
        User user = createUser(signUpRequest);
        UserEntity userEntity = UserEntityMapper.UserToEntity(user);
        Optional<UserEntity> existingUser = repositoryServiceImpl.getUser(userEntity.getLogin());
        if (existingUser.isPresent()) {
            return false;
        } else {
            repositoryServiceImpl.saveUser(userEntity);
            return true;
        }
    }

    @Override
    public void deleteUser(UUID id) {
        Optional<UserEntity> existingUser = repositoryServiceImpl.getUser(id);
        if (existingUser.isPresent()) {
            repositoryServiceImpl.deleteUser(id);
        }
    }

    @Override
    public UUID authorizeUser(String authorizationHeader) {
        String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
        byte[] decodedBytes = Base64.decodeBase64(base64Credentials);
        String credentials = new String(decodedBytes, StandardCharsets.UTF_8);

        // Разделение логина и пароля
        String[] values = credentials.split(":", 2);
        if (values.length != 2) {
            throw new IllegalArgumentException("Invalid authorization header format");
        }

        String login = values[0];
        String password = values[1];

        // Поиск пользователя в базе данных
        Optional<UserEntity> userEntityOptional = repositoryServiceImpl.getUser(login);
        if (userEntityOptional.isEmpty()) {
            throw new SecurityException("User not found");
        }

        UserEntity userEntity = userEntityOptional.get();

        // Проверка пароля
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, userEntity.getPassword())) {
            throw new SecurityException("Invalid password");
        }

        // Возврат UUID пользователя
        return userEntity.getId();
    }

}
