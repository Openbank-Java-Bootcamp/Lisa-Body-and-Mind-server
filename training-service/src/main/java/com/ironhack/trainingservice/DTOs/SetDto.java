package com.ironhack.trainingservice.DTOs;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SetDto {
    @NotNull(message = "sets rest time must be defined")
    private String rest;
    @NotNull(message = "sets must have an exerciseId")
    private Integer exerciseId;
    private Integer exerciseSessionId;
}
