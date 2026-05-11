package com.example.springbootapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "initiatives")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Initiative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String initiative;

    // Use OffsetDateTime, it uses DateTime with the addition of an offset from UTC.
    @CreatedDate
    @Column(updatable = false,  nullable = false)
    private OffsetDateTime createdAt;

    // Many initiatives per goal
    @ManyToOne
    // Specifies the foreign key column in the initiatives table
    // Nullable = false makes it so that every initiative has to be tied to a goal
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;
}
