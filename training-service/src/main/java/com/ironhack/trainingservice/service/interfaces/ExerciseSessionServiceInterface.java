package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.model.ExerciseSession;

import java.time.Instant;
import java.util.List;

public interface ExerciseSessionServiceInterface {
    List<ExerciseSession> findAll();
    List<ExerciseSession> findAllByUserId(Integer userId);
    List<ExerciseSession> findAllByUserIdAndProgramNameAndWorkoutName(Integer userId, String programName, String workoutName);
    List<ExerciseSession> findAllByUserIdAndExerciseType(Integer userId, Integer exerciseTypeId);
    List<ExerciseSession> findAllByUserIdAndStartDate(Integer userId, Instant startDate);
    ExerciseSession findById(Integer id);
    ExerciseSession saveExerciseSession(ExerciseSession exerciseSession);
    ExerciseSession update(Integer id, ExerciseSession exerciseSession);
    ExerciseSession deleteExerciseSession(Integer id);
}
