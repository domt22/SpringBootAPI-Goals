package com.example.springbootapi.dto.goal;

import com.example.springbootapi.dto.initiative.InitiativeRequestDTO;
import com.example.springbootapi.enums.Priority;
import com.example.springbootapi.enums.Status;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalRequestDTO {
    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must be less than 100 characters.")
    private String title;
    private String description;
    @NotBlank(message = "Complete by cannot be blank")
    @FutureOrPresent(message = "Complete by must be in the future.")
    private OffsetDateTime completeBy;
    private Status status;
    @Min(value = 0, message = "Progress must be between 0 and 100.")
    @Max(value = 100, message = "Progress must be between 0 and 100.")
    private int progress;
    private Priority priority;
    private List<InitiativeRequestDTO> initiatives;
}