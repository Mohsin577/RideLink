package com.RideLink.Controller;

import com.RideLink.Entities.User;
import com.RideLink.PayLoad.UserDto;
import com.RideLink.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createuser(@RequestBody UserDto userDto){
        UserDto saveDto = userService.createUser(userDto);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserDto> getusersList(){
        List<UserDto> userDto = userService.getUsers();
        return new ResponseEntity<>(userDto, HttpStatus.OK).getBody();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserDto userDto) {
        UserDto updatedUserDto = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        UserDto userDto = userService.deleteById(id);
        return new ResponseEntity<>("User deleted successfully! ",HttpStatus.OK);
    }
}
