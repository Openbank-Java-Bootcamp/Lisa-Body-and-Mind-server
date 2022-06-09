package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.model.ExerciseType;

import java.util.List;

public interface ExerciseTypeServiceInterface {
    List<ExerciseType> findAll();
    ExerciseType findById(Integer id);
    ExerciseType saveExerciseType(ExerciseType exerciseType);
    ExerciseType update(Integer id, ExerciseType exerciseType);
    ExerciseType deleteExerciseType(Integer id);
}
