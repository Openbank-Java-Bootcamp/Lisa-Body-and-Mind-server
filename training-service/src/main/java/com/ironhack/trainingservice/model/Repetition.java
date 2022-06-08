package com.ironhack.trainingservice.model;

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

    @ManyToOne
    @JoinColumn(name = "set_id")
    private Set set;
}
