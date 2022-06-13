package com.ironhack.trainingservice.DTOs;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExerciseTypeDto {
    @NotNull(message = "exercise type name must be defined")
    private String name;
    private String image;
    @NotNull(message = "exercise type muscle must be defined")
    private String muscle;
    private String equipment;
    private Integer userId;
    private String type;
    private String difficulty;
    private String creator;
    private String instructions;
}
