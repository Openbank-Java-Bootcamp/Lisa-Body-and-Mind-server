package com.ironhack.trainingservice.controller;

import com.ironhack.trainingservice.model.Repetition;
import com.ironhack.trainingservice.service.interfaces.RepetitionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/repetitions")
public class RepetitionController {

    @Autowired
    private RepetitionServiceInterface repetitionServiceInterface;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Repetition> getRepetitions() {
        return repetitionServiceInterface.findAll();
    }

    @GetMapping("/set/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Repetition> getRepetitionsBySet(@PathVariable(name = "id") Integer setId) {
        return repetitionServiceInterface.findAllBySet(setId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Repetition getRepetitionById(@PathVariable(name = "id") Integer repetitionId) {
        return repetitionServiceInterface.findById(repetitionId);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Repetition saveRepetition(@RequestBody @Valid Repetition repetition){
        return repetitionServiceInterface.saveRepetition(repetition);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Repetition updateRepetition(@PathVariable(name = "id") Integer repetitionId, @RequestBody @Valid Repetition repetition){
        return repetitionServiceInterface.update(repetitionId, repetition);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Repetition deleteRepetition(@PathVariable(name = "id") Integer repetitionId){
        return repetitionServiceInterface.deleteRepetition(repetitionId);
    }
}
