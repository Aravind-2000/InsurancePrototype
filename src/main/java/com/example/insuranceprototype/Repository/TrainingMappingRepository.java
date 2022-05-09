package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.TrainingMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingMappingRepository extends JpaRepository<TrainingMapping, Long> {


    @Query(value = "select * from training_mapping where valid_flag = 1", nativeQuery = true)
    List<TrainingMapping> getAllValidTraineeAgents();

    @Query(value = "select * from training_mapping where agent_id = :agentId", nativeQuery = true)
    List<TrainingMapping> getMyTrainings(Long agentId);


}
