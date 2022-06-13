package com.ironhack.trainingservice.DTOs;


import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExerciseDto {
    @NotNull(message = "exercise must have a workout")
    private Integer workoutId;
    @NotNull(message = "exercise must have an exercise type ")
    private Integer exerciseTypeId;
}
