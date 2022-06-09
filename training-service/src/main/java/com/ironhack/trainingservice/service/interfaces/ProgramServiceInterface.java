package com.ironhack.trainingservice.service.interfaces;

import com.ironhack.trainingservice.model.Program;

import java.util.List;

public interface ProgramServiceInterface {
    List<Program> findAll();
    Program findById(Integer id);
    Program saveProgram(Program program);
    Program update(Integer id, Program program);
    Program deleteProgram(Integer id);
}
