package com.example.springbootapi.utils.mappers;

import com.example.springbootapi.dto.initiative.InitiativeRequestDTO;
import com.example.springbootapi.dto.initiative.InitiativeResponseDTO;
import com.example.springbootapi.model.Initiative;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InitiativeMapper {
    @Mapping(source = "goal.id", target = "goalId")
    InitiativeResponseDTO toInitiativeResponseDTO(Initiative initiative);
    Initiative toInitiative(InitiativeResponseDTO initiativeResponseDTO);
    Initiative toInitiative(InitiativeRequestDTO initiativeRequestDTO);
    InitiativeRequestDTO toInitiativeRequestDTO(Initiative initiative);

    void updateInitiativeFromDTO(InitiativeRequestDTO requestDTO, @MappingTarget Initiative initiative);
}
