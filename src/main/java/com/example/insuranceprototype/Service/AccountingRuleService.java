package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.AccountingRule;
import com.example.insuranceprototype.Entity.SubAccountTable;
import com.example.insuranceprototype.Repository.AccountCodeRepository;
import com.example.insuranceprototype.Repository.AccountingRuleRepository;
import com.example.insuranceprototype.Repository.SubAccountCodeRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountingRuleService {

    @Autowired
    private AccountingRuleRepository accountingRuleRepository;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private SubAccountCodeRepository subAccountCodeRepository;


    public List<AccountingRule> getAllAccRule(){
        return accountingRuleRepository.getAllValids();
    }

    public AccountingRule getAccRule(Long id){
        return accountingRuleRepository.findById(id).get();
    }

    public String addAccRule(AccountingRule accountingRule){
        accountingRule.setValidFlag(1);
        accountingRuleRepository.save(accountingRule);
        return errorService.getErrorById("ER001");
    }

    public String updateAccRule(Long id, AccountingRule newOne){

        AccountingRule oldOne = accountingRuleRepository.getById(id);


        if(newOne.getAccountSign() != null){
            oldOne.setAccountSign(newOne.getAccountSign());
        }

        if(newOne.getAccountingRuleId() != null){
            oldOne.setAccountingRuleId(newOne.getAccountingRuleId());
        }

        if(newOne.getAccountCodeId() != null){
            oldOne.setAccountCodeId(newOne.getAccountCodeId());
        }

        if(newOne.getSubAccountCodeId() != null){
            oldOne.setSubAccountCodeId(newOne.getSubAccountCodeId());
            SubAccountTable subAccountTable = subAccountCodeRepository.getById(newOne.getSubAccountCodeId());
            oldOne.setAccountSign(subAccountTable.getAccountSign());
        }

        if(newOne.getCompanyCurrencyId() != null){
            oldOne.setCompanyCurrencyId(newOne.getCompanyCurrencyId());
        }

        accountingRuleRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String softDeleteAccRule(Long id){
        AccountingRule oldOne = accountingRuleRepository.getById(id);
        oldOne.setValidFlag(-1);
        accountingRuleRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String hardDeleteAccRule(Long id){
        accountingRuleRepository.deleteById(id);
        return errorService.getErrorById("ER003");
    }

    public List<AccountingRule> search(String key){
        return accountingRuleRepository.globalSearch(key);
    }


}
