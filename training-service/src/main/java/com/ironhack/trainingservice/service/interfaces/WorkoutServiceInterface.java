package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.model.Workout;

import java.util.List;

public interface WorkoutServiceInterface {
    List<Workout> findAll();
    List<Workout> findAllByProgram(Integer programId);
    Workout findById(Integer id);
    Workout findByName(String workoutName);
    Workout saveWorkout(Workout workout);
    Workout update(Integer id, Workout workout);
    Workout deleteWorkout(Integer id);
}
