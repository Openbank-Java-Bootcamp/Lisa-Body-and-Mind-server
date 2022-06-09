package com.ironhack.trainingservice.repository;

import com.ironhack.trainingservice.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout,Integer> {
    List<Workout> findAllByProgram(Integer programId);
}
