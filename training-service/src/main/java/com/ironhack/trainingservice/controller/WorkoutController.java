package com.ironhack.trainingservice.controller;

import com.ironhack.trainingservice.DTOs.WorkoutDto;
import com.ironhack.trainingservice.model.Workout;
import com.ironhack.trainingservice.service.interfaces.WorkoutServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutServiceInterface workoutServiceInterface;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Workout> getWorkouts() {
        return workoutServiceInterface.findAll();
    }

    @GetMapping("/program/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Workout> getWorkoutsByProgram(@PathVariable(name = "id") Integer programId) {
        return workoutServiceInterface.findAllByProgram(programId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Workout getWorkoutById(@PathVariable(name = "id") Integer workoutId) {
        return workoutServiceInterface.findById(workoutId);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Workout saveWorkout(@RequestBody @Valid WorkoutDto dto){
        return workoutServiceInterface.saveWorkout(dto);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Workout updateWorkout(@PathVariable(name = "id") Integer workoutId, @RequestBody @Valid Workout workout){
        return workoutServiceInterface.update(workoutId, workout);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Workout deleteWorkout(@PathVariable(name = "id") Integer workoutId){
        return workoutServiceInterface.deleteWorkout(workoutId);
    }
}
