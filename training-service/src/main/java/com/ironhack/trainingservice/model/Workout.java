package com.ironhack.trainingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.trainingservice.enums.Creator;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String name;
    private Integer userId;

    @Enumerated(EnumType.STRING)
    private Creator creator;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @OneToMany(mappedBy = "workout")
    @JsonIgnore
    private List<Exercise> exercises;
}
