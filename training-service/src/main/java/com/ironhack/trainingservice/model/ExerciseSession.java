package com.ironhack.trainingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ExerciseSession {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String programName;
    private String workoutName;
    private Boolean isFinished;
    private Integer userId;

    @CreationTimestamp
    private Instant startDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_type_id", referencedColumnName = "id")
    private ExerciseType exerciseType;

    @OneToMany(mappedBy = "exerciseSession", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Set> sets;
}
