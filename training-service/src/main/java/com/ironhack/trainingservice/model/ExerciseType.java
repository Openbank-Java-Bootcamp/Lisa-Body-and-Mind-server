package com.ironhack.trainingservice.model;

import com.ironhack.trainingservice.enums.Creator;
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
    private String muscle;
    private String equipment;
    private String instructions;
    private Integer userId;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    private Creator creator;

    @OneToOne(mappedBy = "exerciseType")
    private Exercise exercise;

    @OneToOne(mappedBy = "exerciseType")
    private ExerciseSession exerciseSession;
}
