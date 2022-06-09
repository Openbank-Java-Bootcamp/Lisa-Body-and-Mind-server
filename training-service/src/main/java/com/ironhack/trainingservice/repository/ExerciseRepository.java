package com.ironhack.trainingservice.repository;

import com.ironhack.trainingservice.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise ,Integer> {
    List<Exercise> findAllByWorkoutId(Integer workoutId);
//    Optional<Exercise> findByName(String exerciseName);
//    Boolean existsExerciseByName(String exerciseName);
}
