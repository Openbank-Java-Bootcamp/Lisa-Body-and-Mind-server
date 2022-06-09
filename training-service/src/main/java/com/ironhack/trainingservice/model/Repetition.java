package com.ironhack.trainingservice.model;

import com.ironhack.trainingservice.enums.WeightSystem;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Repetition {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private Integer weight;

    @Enumerated(EnumType.STRING)
    private WeightSystem weightSystem;

    @ManyToOne
    @JoinColumn(name = "set_id")
    private Set set;
}
