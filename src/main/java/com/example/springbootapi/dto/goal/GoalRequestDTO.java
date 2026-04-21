package com.example.springbootapi.dto.goal;

import com.example.springbootapi.dto.initiative.InitiativeRequestDTO;
import com.example.springbootapi.enums.Priority;
import com.example.springbootapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalRequestDTO {
    private String title;
    private String description;
    private OffsetDateTime completeBy;
    private Status status;
    private int progress;
    private Priority priority;
    private List<InitiativeRequestDTO> initiatives;
}