package com.ironhack.trainingservice.controller;

import com.ironhack.trainingservice.DTOs.SetDto;
import com.ironhack.trainingservice.model.Set;
import com.ironhack.trainingservice.service.interfaces.SetServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/sets")
public class SetController {

    @Autowired
    private SetServiceInterface setServiceInterface;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Set> getSets() {
        return setServiceInterface.findAll();
    }

    @GetMapping("/exercise/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Set> getSetsByExercise(@PathVariable(name = "id") Integer exerciseId) {
        return setServiceInterface.findAllByExercise(exerciseId);
    }

    @GetMapping("/exercise-session/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Set> getSetsByExerciseSession(@PathVariable(name = "id") Integer exerciseSessionId) {
        return setServiceInterface.findAllByExerciseSession(exerciseSessionId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set getSetById(@PathVariable(name = "id") Integer setId) {
        return setServiceInterface.findById(setId);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Set saveSet(@RequestBody @Valid SetDto dto) throws ParseException {
        return setServiceInterface.saveSet(dto);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set updateSet(@PathVariable(name = "id") Integer setId, @RequestBody @Valid Set set){
        return setServiceInterface.update(setId, set);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set deleteSet(@PathVariable(name = "id") Integer setId){
        return setServiceInterface.deleteSet(setId);
    }
}
