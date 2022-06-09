package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.model.Exercise;

import java.util.List;

public interface ExerciseServiceInterface {
    List<Exercise> findAll();
    Exercise findById(Integer id);
    Exercise saveExercise(Exercise exercise);
    Exercise update(Integer id, Exercise exercise);
    Exercise deleteExercise(Integer id);
}
