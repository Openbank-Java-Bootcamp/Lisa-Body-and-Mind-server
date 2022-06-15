package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.DTOs.WorkoutDto;
import com.ironhack.trainingservice.model.Workout;

import java.util.List;

public interface WorkoutServiceInterface {
    List<Workout> findAll();
    List<Workout> findAllByProgram(Integer programId);
    Workout findById(Integer id);
    Workout findByName(String workoutName);
    Workout saveWorkout(WorkoutDto dto);
    Workout update(Integer id, WorkoutDto dto);
    Workout deleteWorkout(Integer id);
}
