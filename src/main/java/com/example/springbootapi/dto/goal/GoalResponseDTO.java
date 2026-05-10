package com.example.springbootapi.dto.goal;

import com.example.springbootapi.dto.initiative.InitiativeResponseDTO;
import com.example.springbootapi.enums.Priority;
import com.example.springbootapi.enums.Status;

import java.time.OffsetDateTime;
import java.util.List;

public record GoalResponseDTO (
    Long id,
    String title,
    String description,
    OffsetDateTime completeBy,
    Status status,
    int progress,
    Priority priority,
    List<InitiativeResponseDTO> initiatives,
    OffsetDateTime createdAt
) {}