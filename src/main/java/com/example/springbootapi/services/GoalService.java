package com.example.springbootapi.services;

import com.example.springbootapi.dto.goal.GoalRequestDTO;
import com.example.springbootapi.dto.goal.GoalResponseDTO;
import com.example.springbootapi.exception.GoalNotFoundException;
import com.example.springbootapi.model.Goal;
import com.example.springbootapi.repo.GoalRepository;
import com.example.springbootapi.repo.InitiativeRepository;
import com.example.springbootapi.utils.mappers.GoalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Makes Constructor with final fields
public class GoalService {
    private final GoalRepository goalRepository;
    private final InitiativeRepository initiativeRepository;
    private final GoalMapper goalMapper;

    // GET: /goals
    public List<GoalResponseDTO> getAllGoals() {
        return goalRepository.findAll()
                .stream()
                .map(goalMapper::toGoalResponseDTO).
                toList();
    }

    // GET: /goals/{id}
    public GoalResponseDTO getGoalById(int id) {
        Goal goal = goalRepository.findById(id).orElseThrow(() -> new GoalNotFoundException(id)); // Custom Exception
        return goalMapper.toGoalResponseDTO(goal);
    }

    // POST: /goals
    @Transactional
    public GoalResponseDTO createGoal(GoalRequestDTO requestDTO) {
        Goal goal = goalMapper.toGoal(requestDTO);
        Goal saved = goalRepository.save(goal);
        return goalMapper.toGoalResponseDTO(saved);
    }

    // PUT: /goals/{id}
    @Transactional
    public GoalResponseDTO updateGoal(int id, GoalRequestDTO requestDTO) {
        return goalRepository.findById(id).map(existing -> {
            existing.setTitle(requestDTO.getTitle());
            existing.setDescription(requestDTO.getDescription());
            existing.setCompleteBy(requestDTO.getCompleteBy());
            existing.setStatus(requestDTO.getStatus());
            existing.setProgress(requestDTO.getProgress());
            existing.setPriority(requestDTO.getPriority());
            Goal updated = goalRepository.save(existing);
            return goalMapper.toGoalResponseDTO(updated);
        })
                .orElseThrow(() -> new GoalNotFoundException(id)); // Custom Exception
    }

    // DELETE: /goals/{id}
    public void deleteGoal(int id) {
        if (!goalRepository.existsById(id)) {
            throw new GoalNotFoundException(id);
        }
        goalRepository.deleteById(id);
    }

}
