package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.DTOs.ProgramDto;
import com.ironhack.trainingservice.enums.Creator;
import com.ironhack.trainingservice.model.Program;

import java.util.List;
import java.util.Optional;

public interface ProgramServiceInterface {
    List<Program> findAll();
    List<Program> findAllByUserId(Integer userId);
    List<Program> findAllByCreator(Creator creator);
    List<Program> findAllByUserIdAndCreator(Integer userId, Creator creator);
    Program findById(Integer id);
    Program findByName(String programName);
    Program saveProgram(ProgramDto dto);
    Program update(Integer id, ProgramDto dto);
    Program deleteProgram(Integer id);
}
