package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.PolicyHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PolicyHeaderRepository extends JpaRepository<PolicyHeader, Long> {


    @Query(value = "select * from policy_header  where is_active = 1", nativeQuery = true)
    List<PolicyHeader> getAllValid();


    @Query(value = "select * from policy_header where policy_number like %:key% and is_active = 1 or company_id like %:key% and is_active = 1 or agent_id like %:key% and is_active = 1 or" +
            "c_status like %:key% and is_active = 1 or cp_status like %:key% and is_active = 1 or premium like %:key% and is_active = 1 ", nativeQuery = true)
    List<PolicyHeader> globalSearch(String key);

}
