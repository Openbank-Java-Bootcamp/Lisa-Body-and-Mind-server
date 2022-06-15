package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.DTOs.RepetitionDto;
import com.ironhack.trainingservice.model.Repetition;

import java.util.List;

public interface RepetitionServiceInterface {
    List<Repetition> findAll();
    List<Repetition> findAllBySet(Integer setId);
    Repetition findById(Integer id);
    Repetition saveRepetition(RepetitionDto dto);
    Repetition update(Integer id, RepetitionDto dto);
    Repetition deleteRepetition(Integer id);
    List<Repetition> deleteRepetitionsBySetId(Integer setId);
}
