package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.CurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion, Long> {

    @Query(value = "select * from currency_conversion where valid_flag = 1", nativeQuery = true)
    List<CurrencyConversion> getAllValids();


//    @Query(value = " select exchange_rate from (select * from currency_conversion group by exchange_rate having start_date <= :transDate \n" +
//            "        AND end_date >= :transDate\n" +
//            "        AND original_currency_code = :orgCode\n" +
//            "        AND account_currency_code = :accCode\n" +
//            "ORDER BY id DESC LIMIT 1 ) as conversion", nativeQuery = true)
//    Double getExchangeRate(LocalDate transDate, Long orgCode, Long accCode);


    @Query(value = "select exchange_rate from currency_conversion where start_date <= :transDate  and end_date >= :transDate  and original_currency_code = :orgCode " +
            "and account_currency_code = :accCode   and  sl_unique_number = :seqNo ", nativeQuery = true)
    Double getExchangeRate(LocalDate transDate, Long orgCode, Long accCode, Long seqNo);

    @Query(value = "select * from currency_conversion where start_date <= :transDate and end_date >= :transDate  and original_currency_code = :orgCode " +
            "and account_currency_code = :accCode ", nativeQuery = true )
    List<CurrencyConversion> getAllWithinDates(LocalDate transDate, Long orgCode, Long accCode);


    @Query(value = "select * from currency_conversion where id = :key and valid_flag = 1 or original_currency_code = :key and valid_flag = 1 or " +
            "account_currency_code = :key and valid_flag = 1 ", nativeQuery = true)
    List<CurrencyConversion> globalSearch(String key);

}
