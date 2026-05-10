package com.example.springbootapi.controller;

import com.example.springbootapi.dto.goal.GoalRequestDTO;
import com.example.springbootapi.dto.goal.GoalResponseDTO;
import com.example.springbootapi.model.Goal;
import com.example.springbootapi.services.GoalService;
import com.example.springbootapi.utils.mappers.GoalMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/goals")
@RequiredArgsConstructor
public class GoalController {
    private final GoalService goalService;
    private final GoalMapper goalMapper;

    @GetMapping
    public ResponseEntity<Page<GoalResponseDTO>> getAllGoals(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<GoalResponseDTO> goals = goalService.getAllGoals(pageable);
        return ResponseEntity.ok(goals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalResponseDTO> getGoalById(@PathVariable Long id) {
        GoalResponseDTO goal = goalService.getGoalById(id);
        return ResponseEntity.ok(goal);
    }

    @PostMapping
    public ResponseEntity<GoalResponseDTO> saveGoal(@Valid @RequestBody GoalRequestDTO goalRequestDTO) {
        GoalResponseDTO saved = goalService.createGoal(goalRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GoalResponseDTO> updateGoal(@PathVariable Long id, @Valid @RequestBody GoalRequestDTO goalRequestDTO) {
        GoalResponseDTO updated = goalService.updateGoal(id, goalRequestDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GoalResponseDTO> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }
}