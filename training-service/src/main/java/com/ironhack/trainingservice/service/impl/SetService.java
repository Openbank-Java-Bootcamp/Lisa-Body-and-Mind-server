package com.ironhack.trainingservice.service.impl;

import com.ironhack.trainingservice.model.Set;
import com.ironhack.trainingservice.repository.SetRepository;
import com.ironhack.trainingservice.service.interfaces.SetServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SetService implements SetServiceInterface {

    @Autowired
    private SetRepository setRepository;

    public List<Set> findAll() {
        List<Set> setList = setRepository.findAll();
        if (setList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Sets found in the database");
        }
        return setList;
    }

    public Set findById(Integer id) {
        return setRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Set not found"));
    }

    public Set saveSet(Set set) {
        if (set.getId() != null) {
            Optional<Set> optionalSet = setRepository.findById(set.getId());
            if (optionalSet.isPresent())
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Set with id " + set.getId() + " already exist");
        }

        try {
            return setRepository.save(set);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Set");
        }
    }

    public Set update(Integer id, Set set) {
        Set setFromDB = setRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Set is not found"));
        set.setId(setFromDB.getId());

        try {
            return setRepository.save(set);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed Set");
        }
    }

    public Set deleteSet(Integer id) {
        Set setFromDB = setRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Set not found"));
        setRepository.deleteById(id);
        return setFromDB;
    }
}
