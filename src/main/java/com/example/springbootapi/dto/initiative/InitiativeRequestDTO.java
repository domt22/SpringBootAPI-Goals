package com.example.springbootapi.dto.initiative;

import jakarta.validation.constraints.NotBlank;

public record InitiativeRequestDTO (
    @NotBlank(message = "Initiative is required.")
    String initiative
) {}