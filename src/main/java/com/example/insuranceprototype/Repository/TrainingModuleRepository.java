package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.TrainingModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingModuleRepository extends JpaRepository<TrainingModule, Long> {

    @Query(value = "select * from training_module where valid_flag = 1", nativeQuery = true)
    List<TrainingModule> getAllValids();
}
