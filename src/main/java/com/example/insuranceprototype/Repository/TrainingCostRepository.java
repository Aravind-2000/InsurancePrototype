package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.TrainingCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingCostRepository extends JpaRepository<TrainingCost, Long> {

    @Query(value = "select * from training_cost where valid_flag = 1", nativeQuery = true)
    List<TrainingCost> getAllValids();
}
