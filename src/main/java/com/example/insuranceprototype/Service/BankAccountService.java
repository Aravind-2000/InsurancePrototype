package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.BankAccount;
import com.example.insuranceprototype.Repository.BankAccountRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {


    @Autowired
    private BankAccountRepository bankRepo;

    @Autowired
    private ErrorService errorService;

    public List<BankAccount> getallbankacc(){
        return bankRepo.getAllActiveAccounts();
    }

    public BankAccount getbankacc(Long id){
        return bankRepo.getActiveBankAccount(id);
    }

    public String addBankacc(BankAccount account){
        account.setIsActive(1);
        bankRepo.save(account);
        return errorService.getErrorById("ER001");
    }

    public String updatebankacc(Long id , BankAccount account){

        BankAccount acc = bankRepo.getById(id);

        if(account.getAccountHolderName() != null){
            acc.setAccountHolderName(account.getAccountHolderName());
        }

        if(account.getAccountNumber() != null){
            acc.setAccountNumber(account.getAccountNumber());
        }

        if(account.getIfscCode() != null){
            acc.setIfscCode(account.getIfscCode());
        }

        if(account.getBankName() != null){
            acc.setBankName(account.getBankName());
        }

        if(account.getBankBranch() != null){
            acc.setBankBranch(account.getBankBranch());
        }

        if(account.getIsActive() == -1){
            acc.setIsActive(-1);
        }

        if(account.getIsActive() == 1){
            acc.setIsActive(1);
        }

        bankRepo.save(acc);
        return errorService.getErrorById("ER003");
    }

}
