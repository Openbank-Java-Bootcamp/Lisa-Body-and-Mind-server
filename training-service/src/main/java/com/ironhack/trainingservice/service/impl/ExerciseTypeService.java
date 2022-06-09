package com.ironhack.trainingservice.service.impl;

import com.ironhack.trainingservice.enums.Creator;
import com.ironhack.trainingservice.enums.Difficulty;
import com.ironhack.trainingservice.enums.Type;
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

    public List<ExerciseType> findAllByUserId(Integer userId) {
        //TODO verify user id exists
        List<ExerciseType> exerciseTypeList = exerciseTypeRepository.findAllByUserId(userId);
        if (exerciseTypeList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Exercise Types with that User ID found in the database");
        }
        return exerciseTypeList;
    }

    public List<ExerciseType> findAllByCreator(Creator creator) {
        List<ExerciseType> exerciseTypeList = exerciseTypeRepository.findAllByCreator(creator);
        if (exerciseTypeList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Exercise Types with that Creator found in the database");
        }
        return exerciseTypeList;
    }

    public List<ExerciseType> findAllByUserIdAndCreator(Integer userId, Creator creator) {
        //TODO verify user id exists
        List<ExerciseType> exerciseTypeList = exerciseTypeRepository.findAllByUserIdAndCreator(userId, creator);
        if (exerciseTypeList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Exercise Types with that User ID or Creator found in the database");
        }
        return exerciseTypeList;
    }

    public List<ExerciseType> findAllByTypeAndDifficultyAndMuscleAndEquipment(Type type, Difficulty difficulty, String muscle, String equipment) {
        //TODO check if all these things exist (if they are .isPresent() -- make optional
        List<ExerciseType> exerciseTypeList = exerciseTypeRepository
                .findAllByTypeAndDifficultyAndMuscleAndEquipment(type, difficulty, muscle, equipment);
        if (exerciseTypeList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "No Exercise Types with that Type, Difficulty, Muscle or Equipment found in the database");
        }
        return exerciseTypeList;
    }

    public ExerciseType findById(Integer id) {
        return exerciseTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Type with that ID not found"));
    }

    public ExerciseType findByName(String exerciseTypeName) {
        return exerciseTypeRepository.findByName(exerciseTypeName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Type with that Name not found"));
    }

    public ExerciseType saveExerciseType(ExerciseType exerciseType) {
        if (exerciseType.getId() != null) {
            Optional<ExerciseType> optionalExerciseType = exerciseTypeRepository.findById(exerciseType.getId());
            if (optionalExerciseType.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Exercise Type with ID " + exerciseType.getId() + " already exist");
        }

        try {
            return exerciseTypeRepository.save(exerciseType);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Exercise Type");
        }
    }

    public ExerciseType update(Integer id, ExerciseType exerciseType) {
        ExerciseType exerciseTypeFromDB = exerciseTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Type with that ID is not found"));
        exerciseType.setId(exerciseTypeFromDB.getId());

        try {
            return exerciseTypeRepository.save(exerciseType);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Exercise Type");
        }
    }

    public ExerciseType deleteExerciseType(Integer id) {
        ExerciseType exerciseTypeFromDB = exerciseTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise Type with that ID not found"));
        exerciseTypeRepository.deleteById(id);
        return exerciseTypeFromDB;
    }
}
