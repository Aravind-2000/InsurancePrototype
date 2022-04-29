package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {


    @Query(value = "select * from training where valid_flag = 1", nativeQuery = true)
    List<Training> getAllValids();

}
