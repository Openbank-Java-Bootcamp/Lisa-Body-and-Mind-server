package com.ironhack.trainingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public Workout(String name, Integer userId, Creator creator, Program program, List<Exercise> exercises) {
        this.name = name;
        this.userId = userId;
        this.creator = creator;
        this.program = program;
        this.exercises = exercises;
    }

    public Workout(String name, Integer userId, Creator creator, Program program) {
        this.name = name;
        this.userId = userId;
        this.creator = creator;
        this.program = program;
    }

    public static Workout fromDto(WorkoutDto dto, Program program) {
        Creator creator = Creator.valueOf(dto.getCreator().toUpperCase());
        Integer userId = dto.getUserId();
        if (creator == Creator.USER && userId == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Workouts created by users must contain User ID");
        if (creator == Creator.TRAINER && userId != null) userId = null;
        return new Workout(dto.getName(), userId, creator, program);
    }
}
