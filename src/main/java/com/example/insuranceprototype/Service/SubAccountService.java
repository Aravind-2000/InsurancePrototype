package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.SubAccountTable;
import com.example.insuranceprototype.Repository.SubAccountCodeRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubAccountService {


    @Autowired
    private SubAccountCodeRepository subAccountCodeRepository;

    @Autowired
    private ErrorService errorService;


    public List<SubAccountTable> getAllSubCodes(){
        return subAccountCodeRepository.getAllValids();
    }

    public SubAccountTable getSubCode(Long id){
        return subAccountCodeRepository.findById(id).get();
    }

    public String addSubCode(SubAccountTable subAccountTable){
        subAccountTable.setValidFlag(1);
        subAccountCodeRepository.save(subAccountTable);
        return errorService.getErrorById("ER001");
    }

    public String updateSubCode( Long id, SubAccountTable newOne){

        SubAccountTable oldOne = subAccountCodeRepository.getById(id);

        if(newOne.getSubAccountCode() != null){
            oldOne.setSubAccountCode(newOne.getSubAccountCode());
        }

        if(newOne.getSubAccountShortDesc() != null){
            oldOne.setSubAccountShortDesc(newOne.getSubAccountShortDesc());
        }

        if(newOne.getSubAccountLongDesc() != null){
            oldOne.setSubAccountLongDesc(newOne.getSubAccountLongDesc());
        }
        if(newOne.getAccountSign() != null){
            oldOne.setAccountSign(newOne.getAccountSign());
        }
        subAccountCodeRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String softDeleteSubCode(Long id){
        SubAccountTable oldOne = subAccountCodeRepository.getById(id);
        oldOne.setValidFlag(-1);
        subAccountCodeRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String hardDeleteSubCode(Long id){
        subAccountCodeRepository.deleteById(id);
        return errorService.getErrorById("ER003");
    }

    public List<SubAccountTable> search(String key){
        return subAccountCodeRepository.globalSearch(key);
    }

}
