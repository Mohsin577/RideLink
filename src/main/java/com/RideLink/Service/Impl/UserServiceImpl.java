package com.RideLink.Service.Impl;

import com.RideLink.Entities.User;
import com.RideLink.PayLoad.UserDto;
import com.RideLink.Repository.UserRepository;
import com.RideLink.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());

        User savedUser = userRepo.save(user);

        UserDto savedUserDto = new UserDto();
        savedUserDto.setId(savedUser.getId());
        savedUserDto.setName(savedUser.getName());
        savedUserDto.setEmail(savedUser.getEmail());
        savedUserDto.setPhone(savedUser.getPhone());

        return savedUserDto;
    }

    @Override
    public List<UserDto> getUsers() {
        // Fetch the list of users from the database
        List<User> users = userRepo.findAll();

        // Convert the list of User entities to a list of UserDto objects
        List<UserDto> userDtos = users.stream().map(user -> {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setPhone(user.getPhone());
            return userDto;
        }).collect(Collectors.toList());

        // Return the list of UserDto objects
        return userDtos;
    }

    @Override
    public UserDto getUserById(long id) {
        User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("User Not Found with id : "+ id));
        // Convert the User entity to a UserDto
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());

        // Return the UserDto object
        return userDto;
    }

    @Override
    public UserDto updateUser(long id,UserDto userDto) {
        User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("user not found with id : "+id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());

        User updateUser = userRepo.save(user);

        userDto.setId(updateUser.getId());

        return userDto;
    }

    @Override
    public UserDto deleteById(long id) {
        User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found with id : "+id));
        userRepo.delete(user);

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        return userDto;
    }


}
