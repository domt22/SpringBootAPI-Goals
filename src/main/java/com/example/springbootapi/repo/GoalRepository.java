package com.example.springbootapi.repo;

import com.example.springbootapi.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {}