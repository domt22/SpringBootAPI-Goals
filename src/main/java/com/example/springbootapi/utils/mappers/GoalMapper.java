package com.example.springbootapi.utils.mappers;

import com.example.springbootapi.dto.goal.GoalRequestDTO;
import com.example.springbootapi.dto.goal.GoalResponseDTO;
import com.example.springbootapi.model.Goal;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {InitiativeMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GoalMapper {
    GoalResponseDTO toGoalResponseDTO(Goal goal);
    Goal toGoal(GoalResponseDTO goalResponseDTO);
    Goal toGoal(GoalRequestDTO goalRequestDTO);
    GoalRequestDTO toGoalRequestDTO(Goal goal);
    void updateGoalFromDTO(GoalRequestDTO goalRequestDTO, @MappingTarget Goal goal);
    @AfterMapping
    default void linkInitiatives(@MappingTarget Goal goal) {
        if (goal.getInitiatives() != null) {
            goal.getInitiatives().forEach(initiative -> initiative.setGoal(goal));
        }
    }
}
