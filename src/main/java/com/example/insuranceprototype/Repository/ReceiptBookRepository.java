package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.ReceiptBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceiptBookRepository extends JpaRepository<ReceiptBook, Long> {

    @Query(value = "Select * from receipt_book where valid_flag = 1", nativeQuery = true)
    List<ReceiptBook> getAllValids();


    @Query(value = "select  * from receipt_book where id like %:key% and valid_flag = 1 or agent_id like %:key% and valid_flag = 1 or original_receipt_currency_id like %:key% and valid_flag = 1 or" +
            " receipt_no like %:key% and valid_flag = 1 or receipt_method like %:key% and valid_flag = 1 or total_receipt_amount like %:key% and valid_flag = 1 ", nativeQuery = true)
    List<ReceiptBook> globalSearch(String key);

}
