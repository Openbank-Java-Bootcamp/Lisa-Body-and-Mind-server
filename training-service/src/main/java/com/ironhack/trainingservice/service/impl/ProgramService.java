package com.ironhack.trainingservice.service.impl;

import com.ironhack.trainingservice.enums.Creator;
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

    public List<Program> findAllByUserId(Integer userId) {
        //TODO validate user ID exists
        List<Program> programList = programRepository.findAllByUserId(userId);
        if (programList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Programs with that User ID found in the database");
        }
        return programList;
    }

    public List<Program> findAllByCreator(Creator creator) {
        List<Program> programList = programRepository.findAllByCreator(creator);
        if (programList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Programs with that Creator found in the database");
        }
        return programList;
    }

    public List<Program> findAllByUserIdAndCreator(Integer userId, Creator creator) {
        //TODO validate user ID exists
        List<Program> programList = programRepository.findAllByUserIdAndCreator(userId, creator);
        if (programList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Programs with that User ID or Creator found in the database");
        }
        return programList;
    }

    public Program findById(Integer id) {
        return programRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program with that ID not found"));
    }

    public Program findByName(String programName) {
        return programRepository.findByName(programName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program with that Name not found"));
    }

    public Program saveProgram(Program program) {
        if (program.getId() != null) {
            Optional<Program> optionalProgram = programRepository.findById(program.getId());
            if (optionalProgram.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Program with ID " + program.getId() + " already exist");
        }

        try {
            return programRepository.save(program);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Program");
        }
    }

    public Program update(Integer id, Program program) {
        Program programFromDB = programRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program with that ID is not found"));
        program.setId(programFromDB.getId());

        try {
            return programRepository.save(program);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Program");
        }
    }

    public Program deleteProgram(Integer id) {
        Program programFromDB = programRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program with that ID not found"));
        programRepository.deleteById(id);
        return programFromDB;
    }
}
