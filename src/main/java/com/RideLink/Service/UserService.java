package com.RideLink.Service;

import com.RideLink.PayLoad.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getUsers();
    UserDto getUserById(long id);
    UserDto updateUser(long id, UserDto userDto);

    UserDto deleteById(long id);
}
