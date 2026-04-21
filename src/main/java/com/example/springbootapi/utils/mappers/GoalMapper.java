package com.example.springbootapi.utils.mappers;

import com.example.springbootapi.dto.goal.GoalRequestDTO;
import com.example.springbootapi.dto.goal.GoalResponseDTO;
import com.example.springbootapi.model.Goal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoalMapper {
    GoalResponseDTO toGoalResponseDTO(Goal goal);
    Goal toGoal(GoalResponseDTO goalResponseDTO);
    Goal toGoal(GoalRequestDTO goalRequestDTO);
    GoalRequestDTO toGoalRequestDTO(Goal goal);
}
