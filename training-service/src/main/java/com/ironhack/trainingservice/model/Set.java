package com.ironhack.trainingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
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
}
