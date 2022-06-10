package com.ironhack.userservice.service.interfaces;

import com.ironhack.userservice.DTOs.UserDto;
import com.ironhack.userservice.model.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> findAll();
    User findById(Integer id);
    User findByEmail(String email);
    User saveUser(UserDto dto);
    User update(Integer id, User user);
    User deleteUser(Integer id);
}
