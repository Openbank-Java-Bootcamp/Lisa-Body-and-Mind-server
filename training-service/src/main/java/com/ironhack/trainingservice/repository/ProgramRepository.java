package com.ironhack.trainingservice.repository;

import com.ironhack.trainingservice.enums.Creator;
import com.ironhack.trainingservice.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Integer> {
    List<Program> findAllByUserId(Integer userId);
    List<Program> findAllByCreator(Creator creator);
    List<Program> findAllByUserIdAndCreator(Integer userId, Creator creator);
    Optional<Program> findByName(String programName);
    Boolean existsProgramByName(String programName);
}
