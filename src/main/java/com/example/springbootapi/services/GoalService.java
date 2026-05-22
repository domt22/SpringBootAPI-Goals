package com.example.springbootapi.services;

import com.example.springbootapi.dto.goal.GoalRequestDTO;
import com.example.springbootapi.dto.goal.GoalResponseDTO;
import com.example.springbootapi.dto.initiative.InitiativeRequestDTO;
import com.example.springbootapi.dto.initiative.InitiativeResponseDTO;
import com.example.springbootapi.exception.GoalNotFoundException;
import com.example.springbootapi.model.Goal;
import com.example.springbootapi.model.Initiative;
import com.example.springbootapi.repo.GoalRepository;
import com.example.springbootapi.repo.InitiativeRepository;
import com.example.springbootapi.utils.mappers.GoalMapper;
import com.example.springbootapi.utils.mappers.InitiativeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor // Makes Constructor with final fields
public class GoalService {
    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;
    private final InitiativeRepository initiativeRepository;
    private final InitiativeMapper initiativeMapper;

    // GET: /goals
    @Transactional(readOnly = true)
    public Page<GoalResponseDTO> getAllGoals(Pageable pageable) {
        // If no sort is specified, sort by id in descending order by default
        Pageable sortedPageable = pageable.getSort().isUnsorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending()) : pageable;

        return goalRepository.findAll(sortedPageable)
                .map(goalMapper::toGoalResponseDTO);
    }

    // GET: /goals/{id}
    @Transactional(readOnly = true)
    public GoalResponseDTO getGoalById(Long id) {
        Goal goal = goalRepository.findById(id).orElseThrow(() -> new GoalNotFoundException(id)); // Custom Exception
        return goalMapper.toGoalResponseDTO(goal);
    }

    // POST: /goals
    @Transactional
    public GoalResponseDTO createGoal(@Valid GoalRequestDTO requestDTO) {
        Goal goal = goalMapper.toGoal(requestDTO);
        Goal saved = goalRepository.save(goal);
        return goalMapper.toGoalResponseDTO(saved);
    }

    // PUT: /goals/{id}
    @Transactional
    public GoalResponseDTO updateGoal(Long id, @Valid GoalRequestDTO requestDTO) {
        Goal existingGoal = goalRepository.findById(id)
                .orElseThrow(() -> new GoalNotFoundException(id));
        goalMapper.updateGoalFromDTO(requestDTO, existingGoal);
        Goal updatedGoal = goalRepository.save(existingGoal);
        return goalMapper.toGoalResponseDTO(updatedGoal);
    }

    // DELETE: /goals/{id}
    @Transactional
    public void deleteGoal(Long id) {
        Goal goal = goalRepository.findById(id).orElseThrow(() -> new GoalNotFoundException(id));
        goalRepository.delete(goal);
    }
}
