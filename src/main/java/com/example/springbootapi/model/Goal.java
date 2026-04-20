package com.example.springbootapi.model;

import com.example.springbootapi.enums.Priority;
import com.example.springbootapi.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "goals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;

    // Use OffsetDateTime, it uses DateTime with the addition of an offset from UTC.
    private OffsetDateTime completeBy;

    // @Enumerated maps the DB enum to the UPPER_CASE Java enums
    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_STARTED;

    @Min(0)
    @Max(100)
    private int progress;

    // @Enumerated maps the DB enum to the UPPER_CASE Java enums
    @Enumerated(EnumType.STRING)
    private Priority priority;

    // One goal to many initiatives
    // mappedBy the Goal object variable "goal", any operations happening on the parent also happens on the child, if initiative is removed, it's deleted from db (orphanRemoval)
    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Initiative> initiatives = new ArrayList<>();

    // Use OffsetDateTime, it uses DateTime with the addition of an offset from UTC.
    private OffsetDateTime createdAt;
}
