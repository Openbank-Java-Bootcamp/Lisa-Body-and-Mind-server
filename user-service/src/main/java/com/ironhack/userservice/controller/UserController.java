package com.ironhack.userservice.controller;

import com.ironhack.userservice.DTOs.UserDto;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.repository.UserRepository;
import com.ironhack.userservice.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userServiceInterface.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable(name = "id") Integer userId) {
        return userServiceInterface.findById(userId);
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByEmail(@PathVariable(name = "email") String userEmail) {
        return userServiceInterface.findByEmail(userEmail);
    }

    @GetMapping("/verify-email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean verifyUserByEmail(@PathVariable(name = "email") String userEmail) {
        return userRepository.existsUserByEmail(userEmail);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Valid UserDto userDto){
        return userServiceInterface.saveUser(userDto);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable(name = "id") Integer userId,@RequestBody @Valid User user){
        return userServiceInterface.update(userId, user);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User deleteUser(@PathVariable(name = "id") Integer userId){
        return userServiceInterface.deleteUser(userId);
    }
}
