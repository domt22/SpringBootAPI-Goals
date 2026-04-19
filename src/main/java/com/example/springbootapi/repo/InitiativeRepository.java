package com.example.springbootapi.repo;

import com.example.springbootapi.model.Initiative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InitiativeRepository extends JpaRepository<Initiative, Integer> {}