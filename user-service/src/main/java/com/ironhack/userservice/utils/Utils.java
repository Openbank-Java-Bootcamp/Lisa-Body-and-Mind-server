package com.ironhack.userservice.utils;

import com.ironhack.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class Utils {

    @Autowired
    private UserRepository userRepository;

    public void validateEmailIsUnique(String email){
        if (userRepository.existsUserByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create User, email already exists");
        }
    }
}
