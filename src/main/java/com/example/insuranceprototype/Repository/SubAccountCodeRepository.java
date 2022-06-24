package com.example.insuranceprototype.Repository;



import com.example.insuranceprototype.Entity.SubAccountTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubAccountCodeRepository extends JpaRepository<SubAccountTable, Long> {

    @Query(value = "select * from sub_account_table where valid_flag = 1", nativeQuery = true)
    List<SubAccountTable> getAllValids();


    @Query(value = "select * from sub_account_table where id like %:key% and valid_flag = 1 or sub_account_code like %:key% and valid_flag = 1 or sub_account_short_desc like %:key% and valid_flag = 1 or" +
            " sub_account_long_desc like %:key% and valid_flag = 1 ", nativeQuery = true)
    List<SubAccountTable> globalSearch(String key);


}
