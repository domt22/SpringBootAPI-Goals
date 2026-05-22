package com.example.springbootapi.services;

import com.example.springbootapi.dto.initiative.InitiativeRequestDTO;
import com.example.springbootapi.dto.initiative.InitiativeResponseDTO;
import com.example.springbootapi.exception.GoalNotFoundException;
import com.example.springbootapi.exception.InitiativeNotFoundException;
import com.example.springbootapi.model.Goal;
import com.example.springbootapi.model.Initiative;
import com.example.springbootapi.repo.GoalRepository;
import com.example.springbootapi.repo.InitiativeRepository;
import com.example.springbootapi.utils.mappers.InitiativeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InitiativeService {
    private final InitiativeRepository initiativeRepository;
    private final InitiativeMapper initiativeMapper;
    private final GoalRepository goalRepository;

    // GET: /goals/{goalID}/initiatives
    @Transactional(readOnly = true)
    public List<InitiativeResponseDTO> getInitiativesByGoal(Long id) {
        if (!goalRepository.existsById(id)) {
            throw new GoalNotFoundException(id);
        }
        return initiativeRepository.findByGoalId(id)
                .stream()
                .map(initiativeMapper::toInitiativeResponseDTO)
                .toList();
    }

    // POST: /goals/{id}/initiatives
    @Transactional
    public InitiativeResponseDTO createInitiative(Long id, @Valid InitiativeRequestDTO requestDTO) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new GoalNotFoundException(id));
        Initiative initiative = initiativeMapper.toInitiative(requestDTO);
        initiative.setGoal(goal);
        Initiative saved = initiativeRepository.save(initiative);
        return initiativeMapper.toInitiativeResponseDTO(saved);
    }

    // PUT: /goals/{goalID}/initiatives/{initiativeID}
    @Transactional
    public InitiativeResponseDTO updateInitiative(Long goalId, Long initiativeId, @Valid InitiativeRequestDTO requestDTO) {
        if (!goalRepository.existsById(goalId)) {
            throw new GoalNotFoundException(goalId);
        }
        Initiative existingInitiative = initiativeRepository.findByIdAndGoalId(initiativeId, goalId)
                .orElseThrow(() -> new InitiativeNotFoundException(initiativeId));
        initiativeMapper.updateInitiativeFromDTO(requestDTO, existingInitiative);
        Initiative updatedInitiative = initiativeRepository.save(existingInitiative);
        return initiativeMapper.toInitiativeResponseDTO(updatedInitiative);
    }

    // DELETE: /goals/{goalID}/initiatives/{initiativeID}
    @Transactional
    public void deleteInitiative(Long goalId, Long initiativeId) {
        if (!goalRepository.existsById(goalId)) {
            throw new GoalNotFoundException(goalId);
        }
        Initiative initiative = initiativeRepository.findByIdAndGoalId(initiativeId, goalId)
                .orElseThrow(() -> new InitiativeNotFoundException(initiativeId));
        initiativeRepository.delete(initiative);
    }
}
