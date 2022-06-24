package com.example.insuranceprototype.Repository;


import com.example.insuranceprototype.Entity.AccountCodeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountCodeRepository extends JpaRepository<AccountCodeTable, Long> {

    @Query(value = "select * from account_code_table where valid_flag = 1", nativeQuery = true)
    List<AccountCodeTable> getAllValids();


    @Query(value = "select * from account_code_table where id like %:key% and valid_flag = 1 or account_code like %:key% and valid_flag = 1 or account_short_description like %:key% and valid_flag = 1 or" +
            " account_long_description like %:key% and valid_flag = 1 ", nativeQuery = true)
    List<AccountCodeTable> globalSearch(String key);


}
