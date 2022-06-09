package com.ironhack.trainingservice.controller;

import com.ironhack.trainingservice.model.ExerciseSession;
import com.ironhack.trainingservice.service.interfaces.ExerciseSessionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/exercise-sessions")
public class ExerciseSessionController {

    @Autowired
    private ExerciseSessionServiceInterface exerciseSessionServiceInterface;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseSession> getExerciseSessions() {
        return exerciseSessionServiceInterface.findAll();
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseSession> getExerciseSessionsByUser(@PathVariable(name = "id") Integer userId) {
        return exerciseSessionServiceInterface.findAllByUserId(userId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseSession getExerciseSessionById(@PathVariable(name = "id") Integer exerciseSessionId) {
        return exerciseSessionServiceInterface.findById(exerciseSessionId);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseSession saveExerciseSession(@RequestBody @Valid ExerciseSession exerciseSession){
        return exerciseSessionServiceInterface.saveExerciseSession(exerciseSession);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseSession updateExerciseSession(@PathVariable(name = "id") Integer exerciseSessionId,@RequestBody @Valid ExerciseSession exerciseSession){
        return exerciseSessionServiceInterface.update(exerciseSessionId, exerciseSession);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseSession deleteExerciseSession(@PathVariable(name = "id") Integer exerciseSessionId){
        return exerciseSessionServiceInterface.deleteExerciseSession(exerciseSessionId);
    }
}
