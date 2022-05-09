package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {


    @Query(value = "select * from training_session where valid_flag = 1", nativeQuery = true)
    List<TrainingSession> getAllValids();

}
