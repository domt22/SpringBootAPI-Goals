package com.example.springbootapi.dto.goal;

import com.example.springbootapi.enums.Priority;
import com.example.springbootapi.enums.Status;
import com.example.springbootapi.model.Initiative;

import java.time.OffsetDateTime;
import java.util.List;

public class GoalResponseDTO {
    private int id;
    private String title;
    private String description;
    private OffsetDateTime completeBy;
    private Status status;
    private int progress;
    private Priority priority;
    private List<Initiative> initiatives;
    private OffsetDateTime createdAt;
}