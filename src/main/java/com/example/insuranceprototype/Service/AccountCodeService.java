package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.AccountCodeTable;
import com.example.insuranceprototype.Repository.AccountCodeRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountCodeService {

    @Autowired
    private AccountCodeRepository accountMasterRepository;

    @Autowired
    private ErrorService errorService;



    public List<AccountCodeTable> getAllAccMaster(){
        return accountMasterRepository.getAllValids();
    }

    public AccountCodeTable getAccMaster(Long id){
        return accountMasterRepository.findById(id).get();
    }

    public String addAccMaster(AccountCodeTable accountMaster){
        accountMaster.setValidFlag(1);
        accountMasterRepository.save(accountMaster);
        return errorService.getErrorById("ER001");
    }

    public String updateAccMaster(Long id, AccountCodeTable newOne){

        AccountCodeTable oldOne = accountMasterRepository.getById(id);

        if(newOne.getAccountCode() != null){
            oldOne.setAccountCode(newOne.getAccountCode());
        }

        if(newOne.getAccountShortDescription() != null){
            oldOne.setAccountShortDescription(newOne.getAccountShortDescription());
        }

        if(newOne.getAccountLongDescription() != null){
            oldOne.setAccountLongDescription(newOne.getAccountLongDescription());
        }

        accountMasterRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String softDeleteAccMaster(Long id){
        AccountCodeTable oldOne = accountMasterRepository.getById(id);
        oldOne.setValidFlag(-1);
        accountMasterRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String hardDeleteAccMaster(Long id){
        accountMasterRepository.deleteById(id);
        return errorService.getErrorById("ER003");
    }

    public List<AccountCodeTable> search(String key){
        return accountMasterRepository.globalSearch(key);
    }


}
