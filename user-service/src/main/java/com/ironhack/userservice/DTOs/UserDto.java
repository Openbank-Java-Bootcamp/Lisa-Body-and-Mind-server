package com.ironhack.userservice.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String email;
    private String username;
    private String fullName;
    private String image;
}
