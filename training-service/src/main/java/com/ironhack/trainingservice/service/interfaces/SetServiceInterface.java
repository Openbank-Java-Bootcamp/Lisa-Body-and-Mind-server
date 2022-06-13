package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.DTOs.SetDto;
import com.ironhack.trainingservice.model.Set;

import java.text.ParseException;
import java.util.List;

public interface SetServiceInterface {
    List<Set> findAll();
    List<Set> findAllByExercise(Integer exerciseId);
    List<Set> findAllByExerciseSession(Integer exerciseSessionId);
    Set findById(Integer id);
    Set saveSet(SetDto dto) throws ParseException;
    Set update(Integer id, Set set);
    Set deleteSet(Integer id);
}
