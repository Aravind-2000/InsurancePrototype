package com.example.insuranceprototype.Repository;


import com.example.insuranceprototype.Entity.Cpstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CpstatusRepository extends JpaRepository<Cpstatus, Long> {


    @Query(value = "select * from cpstatus where valid_flag = 1", nativeQuery = true)
    List<Cpstatus> getAllValids();
}
