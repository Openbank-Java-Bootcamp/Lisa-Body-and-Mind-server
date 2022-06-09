package com.ironhack.trainingservice.repository;

import com.ironhack.trainingservice.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise ,Integer> {
    List<Exercise> findAllByWorkout(Integer workoutId);
}
