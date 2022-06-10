package com.ironhack.trainingservice.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExerciseTypeDto {
    private String name;
    private String image;
    private String muscle;
    private String equipment;
    private Integer userId;
    private String type;
    private String difficulty;
    private String creator;
    private String instructions;
}
