package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.model.ExerciseSession;

import java.util.List;

public interface ExerciseSessionServiceInterface {
    List<ExerciseSession> findAll();
    ExerciseSession findById(Integer id);
    ExerciseSession saveExerciseSession(ExerciseSession exerciseSession);
    ExerciseSession update(Integer id, ExerciseSession exerciseSession);
    ExerciseSession deleteExerciseSession(Integer id);
}
