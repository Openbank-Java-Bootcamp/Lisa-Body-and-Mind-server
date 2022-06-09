package com.ironhack.trainingservice.service.impl;

import com.ironhack.trainingservice.model.Workout;
import com.ironhack.trainingservice.repository.WorkoutRepository;
import com.ironhack.trainingservice.service.interfaces.WorkoutServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService implements WorkoutServiceInterface {

    @Autowired
    private WorkoutRepository workoutRepository;

    public List<Workout> findAll() {
        List<Workout> workoutList = workoutRepository.findAll();
        if (workoutList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Workouts found in the database");
        }
        return workoutList;
    }

    public Workout findById(Integer id) {
        return workoutRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout not found"));
    }

    public Workout saveWorkout(Workout workout) {
        if (workout.getId() != null) {
            Optional<Workout> optionalWorkout = workoutRepository.findById(workout.getId());
            if (optionalWorkout.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Workout with id " + workout.getId() + " already exist");
        }

        try {
            return workoutRepository.save(workout);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Workout");
        }
    }

    public Workout update(Integer id, Workout workout) {
        Workout workoutFromDB = workoutRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout is not found"));
        workout.setId(workoutFromDB.getId());

        try {
            return workoutRepository.save(workout);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Workout");
        }
    }

    public Workout deleteWorkout(Integer id) {
        Workout workoutFromDB = workoutRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout not found"));
        workoutRepository.deleteById(id);
        return workoutFromDB;
    }
}
