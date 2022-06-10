package com.ironhack.trainingservice.repository;

import com.ironhack.trainingservice.enums.Creator;
import com.ironhack.trainingservice.enums.Difficulty;
import com.ironhack.trainingservice.enums.Type;
import com.ironhack.trainingservice.model.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseTypeRepository extends JpaRepository<ExerciseType,Integer> {
    List<ExerciseType> findAllByUserId(Integer userId);
    List<ExerciseType> findAllByCreator(Creator creator);
    List<ExerciseType> findAllByUserIdAndCreator(Integer userId, Creator creator);
    List<ExerciseType> findAllByTypeAndDifficultyAndMuscleAndEquipment(Type type, Difficulty difficulty, String muscle, String equipment);
    Optional<ExerciseType> findByName(String exerciseTypeName);
//    Boolean existsExerciseTypeByName(String exerciseTypeName);
    Boolean existsExerciseTypeByUserIdAndName(Integer userId, String exerciseTypeName);
    Boolean existsExerciseTypeByCreatorAndName(Creator creator, String exerciseTypeName);
}
