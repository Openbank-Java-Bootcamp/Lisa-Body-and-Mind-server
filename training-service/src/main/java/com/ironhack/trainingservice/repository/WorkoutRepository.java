package com.ironhack.trainingservice.repository;

import com.ironhack.trainingservice.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout,Integer> {
    List<Workout> findAllByProgramId(Integer programId);
    Optional<Workout> findByName(String workoutName);
    Boolean existsWorkoutByName(String workoutName);
}
