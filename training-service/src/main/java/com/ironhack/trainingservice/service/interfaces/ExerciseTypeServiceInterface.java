package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.enums.Creator;
import com.ironhack.trainingservice.enums.Difficulty;
import com.ironhack.trainingservice.enums.Type;
import com.ironhack.trainingservice.model.ExerciseType;

import java.util.List;
import java.util.Optional;

public interface ExerciseTypeServiceInterface {
    List<ExerciseType> findAll();
    List<ExerciseType> findAllByUserId(Integer userId);
    List<ExerciseType> findAllByCreator(Creator creator);
    List<ExerciseType> findAllByUserIdAndCreator(Integer userId, Creator creator);
    List<ExerciseType> findAllByTypeAndDifficultyAndMuscleAndEquipment(Type type, Difficulty difficulty, String muscle, String equipment);
    ExerciseType findById(Integer id);
    ExerciseType findByName(String exerciseTypeName);
    ExerciseType saveExerciseType(ExerciseType exerciseType);
    ExerciseType update(Integer id, ExerciseType exerciseType);
    ExerciseType deleteExerciseType(Integer id);
}
