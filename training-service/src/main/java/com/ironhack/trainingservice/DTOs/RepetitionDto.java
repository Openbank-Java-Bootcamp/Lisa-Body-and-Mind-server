package com.ironhack.trainingservice.DTOs;

import com.ironhack.trainingservice.enums.WeightSystem;
import com.ironhack.trainingservice.model.Set;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepetitionDto {
    @NotNull(message = "repetition weight must be defined")
    private Integer weight;
    @NotNull(message = "repetition Weight System must be defined")
    private String weightSystem;
    @NotNull(message = "repetition Set Id must be defined")
    private Integer setId;
}
