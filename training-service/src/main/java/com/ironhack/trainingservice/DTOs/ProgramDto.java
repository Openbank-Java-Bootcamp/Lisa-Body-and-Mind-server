package com.ironhack.trainingservice.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProgramDto {
    private String name;
    private String creator;
    private Integer userId;
}
