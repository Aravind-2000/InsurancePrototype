package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.AccountingRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountingRuleRepository extends JpaRepository<AccountingRule, Long> {

    @Query(value = "select * from accounting_rule where valid_flag = 1", nativeQuery = true)
    List<AccountingRule> getAllValids();


    @Query(value = "select * from accounting_rule where id = :key and valid_flag = 1 or account_code_id = :key and valid_flag = 1 or sub_account_code_id = :key and valid_flag = 1 or account_sign = :key and valid_flag = 1", nativeQuery = true)
    List<AccountingRule> globalSearch(String key);

}
