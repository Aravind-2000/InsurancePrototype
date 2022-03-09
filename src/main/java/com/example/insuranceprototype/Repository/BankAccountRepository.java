package com.example.insuranceprototype.Repository;

import com.example.insuranceprototype.Entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {


    @Query(value = "select * from bank_account where is_active = 1", nativeQuery = true)
    List<BankAccount> getAllActiveAccounts();

    @Query(value = "select * from bank_account where id = :bankid and is_active = 1", nativeQuery = true)
    BankAccount getActiveBankAccount(Long bankid);

    @Query(value = "select * from bank_account where account_number like %:key% and is_active = 1 " +
            "or account_holder_name like %:key% and is_active = 1 " +
            "or bank_name like %:key% and is_active = 1 " +
            "or ifsc_code like %:key% and is_active = 1 or bank_branch like %:key% and is_active = 1 ", nativeQuery = true)
    List<BankAccount> globalSearch(String key);

}
