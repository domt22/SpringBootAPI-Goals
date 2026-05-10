package com.example.springbootapi.repo;

import com.example.springbootapi.dto.initiative.InitiativeResponseDTO;
import com.example.springbootapi.model.Initiative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InitiativeRepository extends JpaRepository<Initiative, Long> {
    List<Initiative> findByGoalId(Long goalId);
    Optional<Initiative> findByIdAndGoalId(Long initiativeID, Long goalID);
}