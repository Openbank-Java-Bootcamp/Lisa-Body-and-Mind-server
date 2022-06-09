package com.ironhack.trainingservice.service.impl;

import com.ironhack.trainingservice.model.ExerciseSession;
import com.ironhack.trainingservice.repository.ExerciseSessionRepository;
import com.ironhack.trainingservice.service.interfaces.ExerciseSessionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseSessionService implements ExerciseSessionServiceInterface {

    @Autowired
    private ExerciseSessionRepository exerciseSessionRepository;

    public List<ExerciseSession> findAll() {
        List<ExerciseSession> exerciseSessionList = exerciseSessionRepository.findAll();
        if (exerciseSessionList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Exercise Sessions found in the database");
        }
        return exerciseSessionList;
    }

    public ExerciseSession findById(Integer id) {
        return exerciseSessionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Session not found"));
    }

    public ExerciseSession saveExerciseSession(ExerciseSession exerciseSession) {
        if (exerciseSession.getId() != null) {
            Optional<ExerciseSession> optionalExerciseSession = exerciseSessionRepository.findById(exerciseSession.getId());
            if (optionalExerciseSession.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Exercise Session with id " + exerciseSession.getId() + " already exist");
        }

        try {
            return exerciseSessionRepository.save(exerciseSession);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Exercise Session");
        }
    }

    public ExerciseSession update(Integer id, ExerciseSession exerciseSession) {
        ExerciseSession exerciseSessionFromDB = exerciseSessionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Session is not found"));
        exerciseSession.setId(exerciseSessionFromDB.getId());

        try {
            return exerciseSessionRepository.save(exerciseSession);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Exercise Session");
        }
    }

    public ExerciseSession deleteExerciseSession(Integer id) {
        ExerciseSession exerciseSessionFromDB = exerciseSessionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Session not found"));
        exerciseSessionRepository.deleteById(id);
        return exerciseSessionFromDB;
    }
}
