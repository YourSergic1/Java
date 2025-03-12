package com.github.YourSergic1.web.mapper;

import com.github.YourSergic1.domain.model.User;
import com.github.YourSergic1.web.model.UserDto;

public class UserDtoMapper {
    public static UserDto UserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setLogin(user.getLogin());
        return userDto;
    }

    public static User DtoToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getLogin(), userDto.getPassword());
    }
}
