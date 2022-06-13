package com.ironhack.trainingservice.model;

import com.ironhack.trainingservice.DTOs.RepetitionDto;
import com.ironhack.trainingservice.enums.WeightSystem;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Repetition {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private Integer weight;

    @Enumerated(EnumType.STRING)
    private WeightSystem weightSystem;

    @ManyToOne
    @JoinColumn(name = "set_id")
    private Set set;

    public Repetition(Integer weight, WeightSystem weightSystem, Set set) {
        this.weight = weight;
        this.weightSystem = weightSystem;
        this.set = set;
    }

    public static Repetition fromDto(RepetitionDto dto, Set set) {
        WeightSystem weightSystem = WeightSystem.valueOf(dto.getWeightSystem().toUpperCase());
        return new Repetition(dto.getWeight(), weightSystem, set);
    }
}
