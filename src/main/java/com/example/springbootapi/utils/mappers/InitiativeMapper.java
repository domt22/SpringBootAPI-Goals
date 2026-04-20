package com.example.springbootapi.utils.mappers;

import com.example.springbootapi.dto.initiative.InitiativeResponseDTO;
import com.example.springbootapi.model.Initiative;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InitiativeMapper {
    InitiativeResponseDTO toInitiativeResponseDTO(Object initiative);
    Initiative toInitiative(InitiativeResponseDTO initiativeResponseDTO);
}
