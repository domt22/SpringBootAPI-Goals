package com.example.springbootapi.utils.mappers;

import com.example.springbootapi.dto.initiative.InitiativeRequestDTO;
import com.example.springbootapi.dto.initiative.InitiativeResponseDTO;
import com.example.springbootapi.model.Initiative;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InitiativeMapper {
    InitiativeResponseDTO toInitiativeResponseDTO(Object initiative);
    Initiative toInitiative(InitiativeResponseDTO initiativeResponseDTO);
    Initiative toInitiative(InitiativeRequestDTO initiativeRequestDTO);

    void updateInitiativeFromDTO(InitiativeRequestDTO requestDTO, @MappingTarget Initiative initiative);
}
