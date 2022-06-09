package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.model.Set;

import java.util.List;

public interface SetServiceInterface {
    List<Set> findAll();
    List<Set> findAllByExercise(Integer exerciseId);
    List<Set> findAllByExerciseSession(Integer exerciseSessionId);
    Set findById(Integer id);
    Set saveSet(Set set);
    Set update(Integer id, Set set);
    Set deleteSet(Integer id);
}
