package com.ironhack.trainingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.trainingservice.DTOs.ProgramDto;
import com.ironhack.trainingservice.enums.Creator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Workout> workouts;

    public Program(String name, Creator creator, Integer userId, List<Workout> workouts) {
        this.name = name;
        this.creator = creator;
        this.userId = userId;
        this.workouts = workouts;
    }

    public Program(String name, Creator creator, Integer userId) {
        this.name = name;
        this.creator = creator;
        this.userId = userId;
    }

    public static Program fromDto(ProgramDto dto) {
        Creator creator = Creator.valueOf(dto.getCreator().toUpperCase());
        Integer userId = dto.getUserId();
        if (creator == Creator.USER && userId == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Programs created by users must contain User ID");
        if (creator == Creator.TRAINER && userId != null) userId = null;
        return new Program(dto.getName(), creator, userId);
    }
}
