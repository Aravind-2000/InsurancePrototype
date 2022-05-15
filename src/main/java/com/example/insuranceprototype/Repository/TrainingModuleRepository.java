package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.TrainingModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingModuleRepository extends JpaRepository<TrainingModule, Long> {

    @Query(value = "select * from training_module where valid_flag = 1", nativeQuery = true)
    List<TrainingModule> getAllValids();


    @Query(value = "select * from training_module where id like %:key% and valid_flag = 1 or training_topic like %:key% and valid_flag = 1 or training_level like %:key%  and valid_flag = 1  " +
            "and valid_flag = 1", nativeQuery = true )
    List<TrainingModule> globalSearch(String key);
}
