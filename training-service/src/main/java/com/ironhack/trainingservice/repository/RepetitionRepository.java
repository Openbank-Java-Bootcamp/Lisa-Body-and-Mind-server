package com.ironhack.trainingservice.repository;

import com.ironhack.trainingservice.model.Repetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepetitionRepository extends JpaRepository<Repetition,Integer> {
    List<Repetition> findAllBySet(Integer setId);
}
