package com.ironhack.trainingservice.repository;

import com.ironhack.trainingservice.model.ExerciseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ExerciseSessionRepository extends JpaRepository<ExerciseSession,Integer> {
    List<ExerciseSession> findAllByUserId(Integer userId);
    List<ExerciseSession> findAllByUserIdAndProgramNameAndWorkoutName(Integer userId, String programName, String workoutName);
    List<ExerciseSession> findAllByUserIdAndExerciseTypeId(Integer userId, Integer exerciseTypeId);
    List<ExerciseSession> findAllByUserIdAndStartDate(Integer userId, Instant startDate);
}
