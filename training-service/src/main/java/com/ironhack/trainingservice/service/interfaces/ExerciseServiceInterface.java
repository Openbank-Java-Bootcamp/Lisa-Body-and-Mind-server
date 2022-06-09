package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.model.Exercise;

import java.util.List;

public interface ExerciseServiceInterface {
    List<Exercise> findAll();
    List<Exercise> findAllByWorkout(Integer workoutId);
    Exercise findById(Integer id);
//    Exercise findByName(String exerciseName);
    Exercise saveExercise(Exercise exercise);
    Exercise update(Integer id, Exercise exercise);
    Exercise deleteExercise(Integer id);
}
