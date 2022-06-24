package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrencyCodeRepository extends JpaRepository<CurrencyCode, Long> {


    @Query(value = "select * from currency_code where valid_flag = 1", nativeQuery = true)
    List<CurrencyCode> getAllValids();


    @Query(value = "select * from currency_code where id like %:key% and valid_flag = 1 or currency_code  like %:key% and valid_flag = 1 or currency_description like %:key% and valid_flag = 1", nativeQuery = true)
    List<CurrencyCode> globalSearch(String key);

}
