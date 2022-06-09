package com.ironhack.trainingservice.service.impl;

import com.ironhack.trainingservice.model.Exercise;
import com.ironhack.trainingservice.repository.ExerciseRepository;
import com.ironhack.trainingservice.service.interfaces.ExerciseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService implements ExerciseServiceInterface {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> findAll() {
        List<Exercise> exerciseList = exerciseRepository.findAll();
        if (exerciseList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Exercises found in the database");
        }
        return exerciseList;
    }

    public Exercise findById(Integer id) {
        return exerciseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found"));
    }

    public Exercise saveExercise(Exercise exercise) {
        if (exercise.getId() != null) {
            Optional<Exercise> optionalExercise = exerciseRepository.findById(exercise.getId());
            if (optionalExercise.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Exercise with id " + exercise.getId() + " already exist");
        }

        try {
            return exerciseRepository.save(exercise);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Exercise");
        }
    }

    public Exercise update(Integer id, Exercise exercise) {
        Exercise exerciseFromDB = exerciseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise is not found"));
        exercise.setId(exerciseFromDB.getId());

        try {
            return exerciseRepository.save(exercise);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Exercise");
        }
    }

    public Exercise deleteExercise(Integer id) {
        Exercise exerciseFromDB = exerciseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found"));
        exerciseRepository.deleteById(id);
        return exerciseFromDB;
    }
}
