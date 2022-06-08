package com.ironhack.trainingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @OneToMany(mappedBy = "exercise")
    @JsonIgnore
    private List<Set> sets;

    @OneToOne
    @JoinColumn(name = "exercise_type_id", referencedColumnName = "id")
    private ExerciseType exerciseType;

//    @ManyToOne
//    @JoinColumn(name = "workout_id")
//    private TrainingSession trainingSession;
}
