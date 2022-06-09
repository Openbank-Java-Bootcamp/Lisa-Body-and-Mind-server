package com.ironhack.trainingservice.service.impl;

import com.ironhack.trainingservice.model.ExerciseType;
import com.ironhack.trainingservice.repository.ExerciseTypeRepository;
import com.ironhack.trainingservice.service.interfaces.ExerciseTypeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseTypeService implements ExerciseTypeServiceInterface {

    @Autowired
    private ExerciseTypeRepository exerciseTypeRepository;

    public List<ExerciseType> findAll() {
        List<ExerciseType> exerciseTypeList = exerciseTypeRepository.findAll();
        if (exerciseTypeList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Exercise Types found in the database");
        }
        return exerciseTypeList;
    }

    public ExerciseType findById(Integer id) {
        return exerciseTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Type not found"));
    }

    public ExerciseType saveExerciseType(ExerciseType exerciseType) {
        if (exerciseType.getId() != null) {
            Optional<ExerciseType> optionalExerciseType = exerciseTypeRepository.findById(exerciseType.getId());
            if (optionalExerciseType.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Exercise Type with id " + exerciseType.getId() + " already exist");
        }

        try {
            return exerciseTypeRepository.save(exerciseType);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Exercise Type");
        }
    }

    public ExerciseType update(Integer id, ExerciseType exerciseType) {
        ExerciseType exerciseTypeFromDB = exerciseTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Type is not found"));
        exerciseType.setId(exerciseTypeFromDB.getId());

        try {
            return exerciseTypeRepository.save(exerciseType);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Exercise Type");
        }
    }

    public ExerciseType deleteExerciseType(Integer id) {
        ExerciseType exerciseTypeFromDB = exerciseTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Type not found"));
        exerciseTypeRepository.deleteById(id);
        return exerciseTypeFromDB;
    }
}
