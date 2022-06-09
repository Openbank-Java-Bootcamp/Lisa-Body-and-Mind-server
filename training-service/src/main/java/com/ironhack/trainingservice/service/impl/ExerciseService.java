package com.ironhack.trainingservice.service.impl;

import com.ironhack.trainingservice.model.Exercise;
import com.ironhack.trainingservice.repository.ExerciseRepository;
import com.ironhack.trainingservice.service.interfaces.ExerciseServiceInterface;
import com.ironhack.trainingservice.service.interfaces.WorkoutServiceInterface;
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

    @Autowired
    private WorkoutServiceInterface workoutServiceInterface;

    public List<Exercise> findAll() {
        List<Exercise> exerciseList = exerciseRepository.findAll();
        if (exerciseList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Exercises found in the database");
        }
        return exerciseList;
    }

    public List<Exercise> findAllByWorkout(Integer workoutId) {
        workoutServiceInterface.findById(workoutId);
        List<Exercise> exerciseList = exerciseRepository.findAllByWorkoutId(workoutId);
        if (exerciseList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Exercises found in the database with that Workout ID");
        }
        return exerciseList;
    }

    public Exercise findById(Integer id) {
        return exerciseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise with that ID not found"));
    }

//    public Exercise findByName(String exerciseName) {
//        return exerciseRepository.findByName(exerciseName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise with that Name not found"));
//    }

    public Exercise saveExercise(Exercise exercise) {
        if (exercise.getId() != null) {
            Optional<Exercise> optionalExercise = exerciseRepository.findById(exercise.getId());
            if (optionalExercise.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Exercise with ID " + exercise.getId() + " already exist");
        }

        try {
            return exerciseRepository.save(exercise);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Exercise");
        }
    }

    public Exercise update(Integer id, Exercise exercise) {
        Exercise exerciseFromDB = exerciseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise with that ID is not found"));
        exercise.setId(exerciseFromDB.getId());

        try {
            return exerciseRepository.save(exercise);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Exercise");
        }
    }

    public Exercise deleteExercise(Integer id) {
        Exercise exerciseFromDB = exerciseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise with that ID not found"));
        exerciseRepository.deleteById(id);
        return exerciseFromDB;
    }
}
