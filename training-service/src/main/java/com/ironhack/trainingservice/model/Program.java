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
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Creator creator;

    private Integer userId;

    @OneToMany(mappedBy = "program")
    @JsonIgnore
    private List<Workout> workouts;
}
