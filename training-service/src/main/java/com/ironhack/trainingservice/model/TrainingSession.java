package com.ironhack.trainingservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TrainingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String programName;
    private String workoutName;
    private Instant startDate;
    private Boolean isFinished;

//    @OneToMany(mappedBy = "")
//    @JsonIgnore
//    private List<Exercise> exercises;

    @ManyToMany
    @JoinTable(
            name = "training_session_sets",
            joinColumns = @JoinColumn(name = "training_session_id"),
            inverseJoinColumns = @JoinColumn(name = "set_id"))
    private java.util.Set<Set> sets;
}
