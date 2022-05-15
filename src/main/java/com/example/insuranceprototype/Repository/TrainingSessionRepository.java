package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {


    @Query(value = "select * from training_session where valid_flag = 1", nativeQuery = true)
    List<TrainingSession> getAllValids();

    @Query(value = "select * from training_session where id like %:key% and valid_flag = 1 or training_module_id like %:key% and valid_flag = 1 or training_type like %:key% and valid_flag = 1 or training_mode like %:key%  and valid_flag = 1  " +
            "and valid_flag = 1", nativeQuery = true )
    List<TrainingSession> globalSearch(String key);

}
