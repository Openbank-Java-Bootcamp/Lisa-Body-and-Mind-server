package com.ironhack.trainingservice.controller;

import com.ironhack.trainingservice.enums.Creator;
import com.ironhack.trainingservice.model.ExerciseType;
import com.ironhack.trainingservice.service.interfaces.ExerciseTypeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/exercise-types")
public class ExerciseTypeController {

    @Autowired
    private ExerciseTypeServiceInterface exerciseTypeServiceInterface;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseType> getExerciseTypes() {
        return exerciseTypeServiceInterface.findAll();
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseType> getExerciseTypesByUser(@PathVariable(name = "id") Integer userId) {
        return exerciseTypeServiceInterface.findAllByUserId(userId);
    }

    @GetMapping("/creator/trainer")
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseType> getExerciseTypesByCreatorTrainer() {
        return exerciseTypeServiceInterface.findAllByCreator(Creator.TRAINER);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseType getExerciseTypeById(@PathVariable(name = "id") Integer exerciseTypeId) {
        return exerciseTypeServiceInterface.findById(exerciseTypeId);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseType saveExerciseType(@RequestBody @Valid ExerciseType exerciseType){
        return exerciseTypeServiceInterface.saveExerciseType(exerciseType);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseType updateExerciseType(@PathVariable(name = "id") Integer exerciseTypeId,@RequestBody @Valid ExerciseType exerciseType){
        return exerciseTypeServiceInterface.update(exerciseTypeId, exerciseType);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseType deleteExerciseType(@PathVariable(name = "id") Integer exerciseTypeId){
        return exerciseTypeServiceInterface.deleteExerciseType(exerciseTypeId);
    }
}
