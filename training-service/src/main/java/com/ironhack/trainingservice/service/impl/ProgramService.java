package com.ironhack.trainingservice.service.impl;

import com.ironhack.trainingservice.model.Program;
import com.ironhack.trainingservice.repository.ProgramRepository;
import com.ironhack.trainingservice.service.interfaces.ProgramServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramService implements ProgramServiceInterface {

    @Autowired
    private ProgramRepository programRepository;

    public List<Program> findAll() {
        List<Program> programList = programRepository.findAll();
        if (programList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Programs found in the database");
        }
        return programList;
    }

    public Program findById(Integer id) {
        return programRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
    }

    public Program saveProgram(Program program) {
        if (program.getId() != null) {
            Optional<Program> optionalProgram = programRepository.findById(program.getId());
            if (optionalProgram.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Program with id " + program.getId() + " already exist");
        }

        try {
            return programRepository.save(program);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Program");
        }
    }

    public Program update(Integer id, Program program) {
        Program programFromDB = programRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program is not found"));
        program.setId(programFromDB.getId());

        try {
            return programRepository.save(program);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Program");
        }
    }

    public Program deleteProgram(Integer id) {
        Program programFromDB = programRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        programRepository.deleteById(id);
        return programFromDB;
    }
}
