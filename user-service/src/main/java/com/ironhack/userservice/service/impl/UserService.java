package com.ironhack.userservice.service.impl;

import com.ironhack.userservice.DTOs.UserDto;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.repository.UserRepository;
import com.ironhack.userservice.service.interfaces.UserServiceInterface;
import com.ironhack.userservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utils utils;

    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Users found in the database");
        }
        return userList;
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with that ID not found"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with that Email not found"));
    }

    public User saveUser(UserDto dto) {
        utils.validateEmailIsUnique(dto.getEmail());

        User user = new User(dto.getEmail(), dto.getUsername(), dto.getFullName(), dto.getImage());

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed User");
        }
    }

    public User update(Integer id, User user) {
        User userFromDB = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with that ID is not found"));
        user.setId(userFromDB.getId());

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed User");
        }
    }

    public User deleteUser(Integer id) {
        User userFromDB = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with that ID not found"));
        userRepository.deleteById(id);
        return userFromDB;
    }
}
