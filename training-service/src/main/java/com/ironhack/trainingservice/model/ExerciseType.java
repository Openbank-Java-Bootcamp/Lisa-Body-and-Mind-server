package com.ironhack.trainingservice.model;

import com.ironhack.trainingservice.DTOs.ExerciseTypeDto;
import com.ironhack.trainingservice.enums.Creator;
import com.ironhack.trainingservice.enums.Difficulty;
import com.ironhack.trainingservice.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ExerciseType {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String name;
    private String image;
    private String muscle;
    private String equipment;

    @Column(length = 3000)
    private String instructions;

    private Integer userId;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    private Creator creator;

    public ExerciseType(String name, String image, String muscle, String equipment, String instructions, Integer userId,
                        Type type, Difficulty difficulty, Creator creator) {
        this.name = name;
        this.image = image;
        this.muscle = muscle;
        this.equipment = equipment;
        this.instructions = instructions;
        this.userId = userId;
        this.type = type;
        this.difficulty = difficulty;
        this.creator = creator;
    }

    public static ExerciseType fromDto(ExerciseTypeDto dto) {
        Integer userId = dto.getUserId();

        Creator creator = Creator.valueOf(dto.getCreator().toUpperCase());
        Type type = dto.getType() != null ? Type.valueOf(dto.getType().toUpperCase()) : Type.STRENGTH;
        Difficulty difficulty = dto.getDifficulty() != null ? Difficulty.valueOf(dto.getDifficulty().toUpperCase())
                : Difficulty.INTERMEDIATE;

        String equipment = dto.getEquipment() != null ? dto.getEquipment() : "other";

        if (creator == Creator.USER && userId == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Exercise Types created by users must contain User ID");
        if (creator == Creator.TRAINER && userId != null) userId = null;

        return new ExerciseType(dto.getName(), dto.getImage(), dto.getMuscle(), dto.getEquipment(), dto.getInstructions(),
                userId, type, difficulty, creator);
    }
}
