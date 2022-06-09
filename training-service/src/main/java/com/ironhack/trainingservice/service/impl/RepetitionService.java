package com.ironhack.trainingservice.service.impl;

import com.ironhack.trainingservice.model.Repetition;
import com.ironhack.trainingservice.repository.RepetitionRepository;
import com.ironhack.trainingservice.service.interfaces.RepetitionServiceInterface;
import com.ironhack.trainingservice.service.interfaces.SetServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RepetitionService implements RepetitionServiceInterface {

    @Autowired
    private RepetitionRepository repetitionRepository;

    @Autowired
    private SetServiceInterface setServiceInterface;

    public List<Repetition> findAll() {
        List<Repetition> repetitionList = repetitionRepository.findAll();
        if (repetitionList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Repetitions found in the database");
        }
        return repetitionList;
    }

    public List<Repetition> findAllBySet(Integer setId) {
        setServiceInterface.findById(setId);
        List<Repetition> repetitionList = repetitionRepository.findAllBySetId(setId);
        if (repetitionList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Repetitions with that Set ID found in the database");
        }
        return repetitionList;
    }

    public Repetition findById(Integer id) {
        return repetitionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Repetition with that ID not found"));
    }

    public Repetition saveRepetition(Repetition repetition) {
        if (repetition.getId() != null) {
            Optional<Repetition> optionalRepetition = repetitionRepository.findById(repetition.getId());
            if (optionalRepetition.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Repetition with ID " + repetition.getId() + " already exist");
        }

        try {
            return repetitionRepository.save(repetition);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Repetition");
        }
    }

    public Repetition update(Integer id, Repetition repetition) {
        Repetition repetitionFromDB = repetitionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Repetition with that ID is not found"));
        repetition.setId(repetitionFromDB.getId());

        try {
            return repetitionRepository.save(repetition);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Repetition");
        }
    }

    public Repetition deleteRepetition(Integer id) {
        Repetition repetitionFromDB = repetitionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Repetition with that ID not found"));
        repetitionRepository.deleteById(id);
        return repetitionFromDB;
    }
}
