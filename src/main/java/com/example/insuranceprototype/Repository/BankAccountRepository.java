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


}
