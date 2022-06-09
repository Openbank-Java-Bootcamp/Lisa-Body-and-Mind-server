package com.ironhack.trainingservice.repository;

import com.ironhack.trainingservice.model.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetRepository extends JpaRepository<Set,Integer> {
    List<Set> findAllByExercise(Integer exerciseId);
    List<Set> findAllByExerciseSession(Integer exerciseSessionId);
}
