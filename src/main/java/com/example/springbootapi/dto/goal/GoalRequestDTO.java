package com.example.springbootapi.dto.goal;

import com.example.springbootapi.dto.initiative.InitiativeRequestDTO;
import com.example.springbootapi.enums.Priority;
import com.example.springbootapi.enums.Status;
import jakarta.validation.constraints.*;

import java.time.OffsetDateTime;
import java.util.List;

public record GoalRequestDTO (
    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must be less than 100 characters.")
    String title,
    String description,
    @NotNull
    @FutureOrPresent(message = "Complete by must be in the future.")
    OffsetDateTime completeBy,
    Status status,
    @Min(value = 0, message = "Progress must be between 0 and 100.")
    @Max(value = 100, message = "Progress must be between 0 and 100.")
    Integer progress,
    @NotNull(message = "Priority is required.")
    Priority priority,
    List<InitiativeRequestDTO> initiatives
) {}