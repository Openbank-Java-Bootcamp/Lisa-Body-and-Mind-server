package com.ironhack.trainingservice.DTOs;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProgramDto {
    @NotNull(message = "program name must be defined")
    private String name;
    @NotNull(message = "creator field must be defined")
    private String creator;
    private Integer userId;
}
