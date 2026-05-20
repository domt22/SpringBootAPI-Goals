package com.example.springbootapi.utils.mappers;

import com.example.springbootapi.dto.goal.GoalRequestDTO;
import com.example.springbootapi.dto.goal.GoalResponseDTO;
import com.example.springbootapi.model.Goal;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = {InitiativeMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface GoalMapper {
    GoalResponseDTO toGoalResponseDTO(Goal goal);
    Goal toGoal(GoalResponseDTO goalResponseDTO);
    @Mapping(target = "status", defaultValue = "NOT_STARTED")
    @Mapping(target = "progress", defaultValue = "0")
    @Mapping(target = "initiatives", defaultExpression = "java(new ArrayList<>())")
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
