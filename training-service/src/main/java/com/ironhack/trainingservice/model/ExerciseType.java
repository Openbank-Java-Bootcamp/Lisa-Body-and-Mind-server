package com.ironhack.trainingservice.model;

import com.ironhack.trainingservice.enums.Difficulty;
import com.ironhack.trainingservice.enums.Type;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ExerciseType {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String name;
    private String image;
    private Type type;
    private String muscle;
    private String equipment;
    private Difficulty difficulty;
    private String instructions;
    private Integer userId;

    @OneToOne(mappedBy = "exerciseType")
    private Exercise exercise;
}
