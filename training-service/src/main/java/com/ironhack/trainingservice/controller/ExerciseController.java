package com.ironhack.trainingservice.controller;

import com.ironhack.trainingservice.DTOs.ExerciseDto;
import com.ironhack.trainingservice.model.Exercise;
import com.ironhack.trainingservice.service.interfaces.ExerciseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseServiceInterface exerciseServiceInterface;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Exercise> getExercises() {
        return exerciseServiceInterface.findAll();
    }

    @GetMapping("/workout/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Exercise> getExercisesByWorkout(@PathVariable(name = "id") Integer workoutId) {
        return exerciseServiceInterface.findAllByWorkout(workoutId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Exercise getExerciseById(@PathVariable(name = "id") Integer exerciseId) {
        return exerciseServiceInterface.findById(exerciseId);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Exercise saveExercise(@RequestBody @Valid ExerciseDto dto){
        return exerciseServiceInterface.saveExercise(dto);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Exercise updateExercise(@PathVariable(name = "id") Integer exerciseId, @RequestBody @Valid ExerciseDto dto){
        return exerciseServiceInterface.update(exerciseId, dto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Exercise deleteExercise(@PathVariable(name = "id") Integer exerciseId){
        return exerciseServiceInterface.deleteExercise(exerciseId);
    }
}
