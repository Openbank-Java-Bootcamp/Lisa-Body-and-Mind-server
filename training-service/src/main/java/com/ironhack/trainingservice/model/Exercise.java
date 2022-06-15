package com.ironhack.trainingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.trainingservice.DTOs.ExerciseDto;
import com.ironhack.trainingservice.DTOs.WorkoutDto;
import com.ironhack.trainingservice.enums.Creator;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Set> sets;

    @OneToOne
    @JoinColumn(name = "exercise_type_id", referencedColumnName = "id")
    private ExerciseType exerciseType;

    public Exercise(Workout workout, List<Set> sets, ExerciseType exerciseType) {
        this.workout = workout;
        this.sets = sets;
        this.exerciseType = exerciseType;
    }

    public Exercise(Workout workout, ExerciseType exerciseType) {
        this.workout = workout;
        this.exerciseType = exerciseType;
    }

    public static Exercise fromDto(Workout workout, ExerciseType exerciseType) {
        return new Exercise(workout, exerciseType);
    }
}
