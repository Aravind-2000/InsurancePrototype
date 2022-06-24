package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.TransactionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionCodeRepository extends JpaRepository<TransactionCode, Long> {

    @Query(value = "select * from transaction_code where valid_flag = 1", nativeQuery = true)
    List<TransactionCode> getALlValids();


    @Query(value = "select * from transaction_code where id like %:key% and valid_flag = 1 or transaction_code like %:key% and valid_flag = 1 or transaction_desc like %:key% and valid_flag = 1", nativeQuery = true)
    List<TransactionCode> globalSearch(String key);

}
