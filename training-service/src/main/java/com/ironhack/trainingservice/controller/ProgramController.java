package com.ironhack.trainingservice.controller;

import com.ironhack.trainingservice.DTOs.ProgramDto;
import com.ironhack.trainingservice.enums.Creator;
import com.ironhack.trainingservice.model.Program;
import com.ironhack.trainingservice.service.interfaces.ProgramServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    @Autowired
    private ProgramServiceInterface programServiceInterface;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Program> getPrograms() {
        return programServiceInterface.findAll();
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Program> getProgramsByUser(@PathVariable(name = "id") Integer userId) {
        return programServiceInterface.findAllByUserId(userId);
    }

    @GetMapping("/creator/trainer")
    @ResponseStatus(HttpStatus.OK)
    public List<Program> getProgramsByCreatorTrainer() {
        return programServiceInterface.findAllByCreator(Creator.TRAINER);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Program getProgramById(@PathVariable(name = "id") Integer programId) {
        return programServiceInterface.findById(programId);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Program saveProgram(@RequestBody @Valid ProgramDto dto){
        return programServiceInterface.saveProgram(dto);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Program updateProgram(@PathVariable(name = "id") Integer programId, @RequestBody @Valid ProgramDto dto){
        return programServiceInterface.update(programId, dto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Program deleteProgram(@PathVariable(name = "id") Integer programId){
        return programServiceInterface.deleteProgram(programId);
    }
}
