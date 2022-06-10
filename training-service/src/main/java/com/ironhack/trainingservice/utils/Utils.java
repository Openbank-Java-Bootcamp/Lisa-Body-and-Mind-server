package com.ironhack.trainingservice.utils;

import com.ironhack.trainingservice.enums.Creator;
import com.ironhack.trainingservice.repository.ExerciseTypeRepository;
import com.ironhack.trainingservice.repository.ProgramRepository;
import com.ironhack.trainingservice.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class Utils {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private ExerciseTypeRepository exerciseTypeRepository;

    public void validateProgramNameIsUnique(String name, Integer userId){
        if(userId!=null)
        if (programRepository.existsProgramByUserIdAndName(userId,name)
                &&programRepository.existsProgramByCreatorAndName(Creator.TRAINER, name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create Program, name already exists");
        }
        else
        if (programRepository.existsProgramByCreatorAndName(Creator.TRAINER, name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create Program, name already exists");
        }
    }

    public void validateExerciseTypeNameIsUnique(String name, Integer userId){
        if(userId!=null)
        if (exerciseTypeRepository.existsExerciseTypeByUserIdAndName(userId, name)
                &&exerciseTypeRepository.existsExerciseTypeByCreatorAndName(Creator.TRAINER, name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create Exercise Type, name already exists");
        }

        else
        if (exerciseTypeRepository.existsExerciseTypeByCreatorAndName(Creator.TRAINER, name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create Exercise Type, name already exists");
        }
    }
}