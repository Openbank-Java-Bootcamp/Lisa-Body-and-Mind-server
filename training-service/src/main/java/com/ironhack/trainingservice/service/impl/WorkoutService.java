package com.ironhack.trainingservice.service.impl;

import com.ironhack.trainingservice.DTOs.WorkoutDto;
import com.ironhack.trainingservice.model.Program;
import com.ironhack.trainingservice.model.Workout;
import com.ironhack.trainingservice.repository.WorkoutRepository;
import com.ironhack.trainingservice.service.interfaces.ProgramServiceInterface;
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

    @Autowired
    private ProgramServiceInterface programServiceInterface;

    public List<Workout> findAll() {
        List<Workout> workoutList = workoutRepository.findAll();
        if (workoutList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Workouts found in the database");
        }
        return workoutList;
    }

    public List<Workout> findAllByProgram(Integer programId) {
        programServiceInterface.findById(programId);
        List<Workout> workoutList = workoutRepository.findAllByProgramId(programId);
        if (workoutList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Workouts with that Program ID found in the database");
        }
        return workoutList;
    }

    public Workout findById(Integer id) {
        return workoutRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout with that ID not found"));
    }

    public Workout findByName(String workoutName) {
        return workoutRepository.findByName(workoutName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout with that Name not found"));
    }

    public Workout saveWorkout(WorkoutDto dto) {
        Program program = programServiceInterface.findById(dto.getProgramId());
        Workout workout = Workout.fromDto(dto, program);

        try {
            return workoutRepository.save(workout);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Workout");
        }
    }

    public Workout update(Integer id, WorkoutDto dto) {
        Workout workoutFromDB = workoutRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout with that ID is not found"));
        Program program = programServiceInterface.findById(dto.getProgramId());

        Workout workout = Workout.fromDto(dto, program);
        workout.setId(workoutFromDB.getId());

        try {
            return workoutRepository.save(workout);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Workout");
        }
    }

    public Workout deleteWorkout(Integer id) {
        Workout workoutFromDB = workoutRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout with that ID not found"));
        workoutRepository.deleteById(id);
        return workoutFromDB;
    }
}
