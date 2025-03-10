package com.github.YourSergic1.datasource.mapper;

import com.github.YourSergic1.datasource.model.UserEntity;
import com.github.YourSergic1.domain.model.User;

public class UserEntityMapper {
    public static UserEntity UserToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setPassword(user.getPassword());
        userEntity.setLogin(user.getLogin());
        return userEntity;
    }

    public static User EntityToUser(UserEntity user) {
        return new User(user.getId(), user.getLogin(), user.getPassword());
    }
}
