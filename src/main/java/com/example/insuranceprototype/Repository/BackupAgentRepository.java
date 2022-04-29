package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.BackupAgentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BackupAgentRepository extends JpaRepository<BackupAgentDetails, Long> {


    @Modifying
    @Transactional
    @Query(value = "insert into backup_agent_details select * from agent_appointment_details where id = :agentId", nativeQuery = true)
    void insertIntoBackupAgent(Long agentId);
}
