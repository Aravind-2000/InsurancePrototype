package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.PolicyCover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PolicyCoverRepository extends JpaRepository<PolicyCover, Long> {


    @Query(value = "select * from policy_cover where valid_flag = 1", nativeQuery = true)
    List<PolicyCover> getAllValid();

    @Query(value = "select * from policy_cover where policy_number like %:key% and valid_flag = 1 or coverage like %:key% and valid_flag = 1 or life like %:key% and valid_flag = 1 or " +
            " coverage_name like %:key% and valid_flag = 1 or instant_premium like %:key% and valid_flag = 1 or sum_assured like %:key% and valid_flag = 1 or c_status like %:key% and valid_flag = 1 or " +
            " company_id like %:key% and valid_flag = 1", nativeQuery = true)
    List<PolicyCover> globalSearch(String key);

}
