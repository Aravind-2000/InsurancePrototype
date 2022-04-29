package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.AgentAppointmentDetails;
import com.example.insuranceprototype.Entity.AgentTrainingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgentTrainingRepository extends JpaRepository<AgentTrainingDetails, Long> {


    @Query(value = "select * from agent_training_details where valid_flag = 1", nativeQuery = true)
    List<AgentTrainingDetails> getAllValidTraineeAgents();

    @Query(value = "select * from agent_training_details where agent_id = :agentId", nativeQuery = true)
    List<AgentTrainingDetails> getMyTrainings(Long agentId);


}
