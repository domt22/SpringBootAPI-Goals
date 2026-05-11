package com.example.springbootapi.dto.initiative;

import java.time.OffsetDateTime;

public record InitiativeResponseDTO (
    int id,
    String initiative,
    OffsetDateTime createdAt,
    int goalId
) {}