package com.ironhack.trainingservice.service.impl;

import com.ironhack.trainingservice.model.ExerciseSession;
import com.ironhack.trainingservice.repository.ExerciseSessionRepository;
import com.ironhack.trainingservice.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseSessionService implements ExerciseSessionServiceInterface {

    @Autowired
    private ExerciseSessionRepository exerciseSessionRepository;

    @Autowired
    private ProgramServiceInterface programServiceInterface;

    @Autowired
    private WorkoutServiceInterface workoutServiceInterface;

    @Autowired
    private ExerciseTypeServiceInterface exerciseTypeServiceInterface;

    public List<ExerciseSession> findAll() {
        List<ExerciseSession> exerciseSessionList = exerciseSessionRepository.findAll();
        if (exerciseSessionList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Exercise Sessions found in the database");
        }
        return exerciseSessionList;
    }

    public List<ExerciseSession> findAllByUserId(Integer userId) {
        //TODO verify userId exists
        List<ExerciseSession> exerciseSessionList = exerciseSessionRepository.findAllByUserId(userId);
        if (exerciseSessionList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "No Exercise Sessions found in the database with that User ID");
        }
        return exerciseSessionList;
    }

    public List<ExerciseSession> findAllByUserIdAndProgramNameAndWorkoutName(Integer userId, String programName, String workoutName) {
        //TODO verify userId exists
        programServiceInterface.findByName(programName);
        workoutServiceInterface.findByName(workoutName);
        List<ExerciseSession> exerciseSessionList = exerciseSessionRepository
                .findAllByUserIdAndProgramNameAndWorkoutName(userId, programName, workoutName);
        if (exerciseSessionList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "No Exercise Sessions found in the database with that User ID, Program Name or Workout Name");
        }
        return exerciseSessionList;
    }

    public List<ExerciseSession> findAllByUserIdAndExerciseType(Integer userId, Integer exerciseTypeId) {
        //TODO verify userId exists
        exerciseTypeServiceInterface.findById(exerciseTypeId);
        List<ExerciseSession> exerciseSessionList = exerciseSessionRepository.findAllByUserIdAndExerciseTypeId(userId, exerciseTypeId);
        if (exerciseSessionList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "No Exercise Sessions found in the database with that User ID or Exercise Type ID");
        }
        return exerciseSessionList;
    }

    public List<ExerciseSession> findAllByUserIdAndStartDate(Integer userId, Instant startDate) {
        //TODO verify userId exists
        List<ExerciseSession> exerciseSessionList = exerciseSessionRepository.findAllByUserIdAndStartDate(userId, startDate);
        if (exerciseSessionList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "No Exercise Sessions found in the database with that User ID or Start Date");
        }
        return exerciseSessionList;
    }

    public ExerciseSession findById(Integer id) {
        return exerciseSessionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Session with that ID not found"));
    }

    public ExerciseSession saveExerciseSession(ExerciseSession exerciseSession) {
        if (exerciseSession.getId() != null) {
            Optional<ExerciseSession> optionalExerciseSession = exerciseSessionRepository.findById(exerciseSession.getId());
            if (optionalExerciseSession.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Exercise Session with ID " + exerciseSession.getId() + " already exist");
        }
        exerciseTypeServiceInterface.findById(exerciseSession.getExerciseType().getId());
        programServiceInterface.findByName(exerciseSession.getProgramName());
        workoutServiceInterface.findByName(exerciseSession.getWorkoutName());

        try {
            return exerciseSessionRepository.save(exerciseSession);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Exercise Session");
        }
    }

    public ExerciseSession update(Integer id, ExerciseSession exerciseSession) {
        ExerciseSession exerciseSessionFromDB = exerciseSessionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Session with that ID is not found"));
        exerciseSession.setId(exerciseSessionFromDB.getId());

        try {
            return exerciseSessionRepository.save(exerciseSession);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Exercise Session");
        }
    }

    public ExerciseSession deleteExerciseSession(Integer id) {
        ExerciseSession exerciseSessionFromDB = exerciseSessionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Session with that ID not found"));
        exerciseSessionRepository.deleteById(id);
        return exerciseSessionFromDB;
    }
}
