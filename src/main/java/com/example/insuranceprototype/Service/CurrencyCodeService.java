package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.CurrencyCode;
import com.example.insuranceprototype.Repository.CurrencyCodeRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CurrencyCodeService {

    @Autowired
    private CurrencyCodeRepository currencyCodeRepository;

    @Autowired
    private ErrorService errorService;


    public List<CurrencyCode> getAllCurrCodes(){
        return currencyCodeRepository.getAllValids();
    }

    public CurrencyCode getCurrCode(Long id){
        return currencyCodeRepository.getById(id);
    }

    public String addCurrCode(CurrencyCode currencyCode){
        currencyCode.setValidFlag(1);
        currencyCodeRepository.save(currencyCode);
        return errorService.getErrorById("ER001");
    }

    public String updateCurrCode(Long id, CurrencyCode newOne){

        CurrencyCode oldOne = currencyCodeRepository.getById(id);

        if(newOne.getCurrencyCode() != null){
            oldOne.setCurrencyCode(newOne.getCurrencyCode());
        }

        if(newOne.getCurrencyDescription() != null){
            oldOne.setCurrencyDescription(newOne.getCurrencyDescription());
        }
        currencyCodeRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String softDeleteCurrCode(Long id){
        CurrencyCode oldOne = currencyCodeRepository.getById(id);
        oldOne.setValidFlag(-1);
        currencyCodeRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String hardDeleteCurrCode(Long id){
        currencyCodeRepository.deleteById(id);
        return errorService.getErrorById("ER003");
    }

    public List<CurrencyCode> search(String key){
        return currencyCodeRepository.globalSearch(key);
    }

}
