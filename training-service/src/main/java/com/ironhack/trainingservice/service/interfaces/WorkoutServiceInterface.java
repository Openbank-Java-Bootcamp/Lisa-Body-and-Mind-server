package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.model.Workout;

import java.util.List;

public interface WorkoutServiceInterface {
    List<Workout> findAll();
    Workout findById(Integer id);
    Workout saveWorkout(Workout workout);
    Workout update(Integer id, Workout workout);
    Workout deleteWorkout(Integer id);
}
