package com.ironhack.trainingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.trainingservice.DTOs.SetDto;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sets")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private Time rest;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @OneToMany(mappedBy = "set")
    @JsonIgnore
    private List<Repetition> repetitions;

    @ManyToOne
    @JoinColumn(name = "exercise_session_id")
    private ExerciseSession exerciseSession;

    public Set(Time rest, Exercise exercise, List<Repetition> repetitions, ExerciseSession exerciseSession) {
        this.rest = rest;
        this.exercise = exercise;
        this.repetitions = repetitions;
        this.exerciseSession = exerciseSession;
    }

    public Set(Time rest, Exercise exercise, ExerciseSession exerciseSession) {
        this.rest = rest;
        this.exercise = exercise;
        this.exerciseSession = exerciseSession;
    }

    public static Set fromDto(SetDto dto, Exercise exercise, ExerciseSession exerciseSession) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Time time = new Time(formatter.parse(dto.getRest()).getTime());
        return new Set(time, exercise, exerciseSession);
    }
}
