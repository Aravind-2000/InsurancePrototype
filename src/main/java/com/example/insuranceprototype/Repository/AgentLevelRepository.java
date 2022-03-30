package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.AgentTypeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgentLevelRepository extends JpaRepository<AgentTypeLevel, Long> {

    @Query(value = "select * from agent_type_level where is_valid = 1", nativeQuery = true)
    List<AgentTypeLevel> getAllActiveTypes();
}
