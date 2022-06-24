package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.TransactionJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionJournalRepository extends JpaRepository<TransactionJournal, Long> {

    @Query(value = "select * from transaction_journal where valid_flag = 1", nativeQuery = true)
    List<TransactionJournal> getAllValids();


    @Query(value = "Select * from transaction_journal where id like %:key% and valid_flag = 1 or agent_id like %:key% and valid_flag = 1 or accounting_rule_id like %:key% and valid_flag = 1 or " +
            " transaction_id like %:key% and valid_flag = 1 or original_currency_id like %:key% and valid_flag = 1 or account_currency_id like %:key% and valid_flag = 1" +
            " or receipt_id like %:key% and valid_flag = 1 or account_sign like %:key% and valid_flag = 1 ", nativeQuery = true)
    List<TransactionJournal> globalSearch(String key);

    @Query(value = "select * from transaction_journal where agent_id = :agentId", nativeQuery = true)
    List<TransactionJournal> basedUponAgentId(Long agentId);
}
