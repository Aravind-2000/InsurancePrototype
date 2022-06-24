package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.CurrencyConversion;
import com.example.insuranceprototype.Repository.CurrencyConversionRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class CurrencyConversionService {

    @Autowired
    private CurrencyConversionRepository currencyConversionRepository;

    @Autowired
    private ErrorService errorService;



    public List<CurrencyConversion> getAllCurrConv(){
        return currencyConversionRepository.getAllValids();
    }

    public CurrencyConversion getCurrConv(Long id){
        return currencyConversionRepository.getById(id);
    }

    public String addCurrConv(CurrencyConversion currencyConversion){
        currencyConversion.setValidFlag(1);
        currencyConversionRepository.save(currencyConversion);
        return errorService.getErrorById("ER001");
    }

    public String updateCurrConv(Long id, CurrencyConversion newOne){


        CurrencyConversion oldOne = currencyConversionRepository.getById(id);

        if(newOne.getSlUniqueNumber() != null){
            oldOne.setSlUniqueNumber(newOne.getSlUniqueNumber());
        }

        if(newOne.getOriginalCurrencyCode() != null){
            oldOne.setOriginalCurrencyCode(newOne.getOriginalCurrencyCode());
        }

        if(newOne.getAccountCurrencyCode() != null){
            oldOne.setAccountCurrencyCode(newOne.getAccountCurrencyCode());
        }

        if(newOne.getStartDate() != null){
            oldOne.setStartDate(newOne.getStartDate());
        }

        if(newOne.getEndDate() != null){
            oldOne.setEndDate(newOne.getEndDate());
        }

        if(newOne.getOriginalCurrencyUnit() != null){
            oldOne.setOriginalCurrencyUnit(newOne.getOriginalCurrencyUnit());
        }

        if(newOne.getExchangeRate() != null){
            oldOne.setExchangeRate(newOne.getExchangeRate());
        }
        currencyConversionRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String softDeleteCurrConv(Long id){
        CurrencyConversion oldOne = currencyConversionRepository.getById(id);
        oldOne.setValidFlag(-1);
        currencyConversionRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String hardDeleteCurrConv(Long id){
        currencyConversionRepository.deleteById(id);
        return errorService.getErrorById("ER003");
    }

    public Double getExchangeRate(String date, Long orgCode, Long accCode, Long sqNo){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate date_1 = LocalDate.parse(date, formatter);
        return currencyConversionRepository.getExchangeRate(date_1, orgCode, accCode, sqNo);
    }

    public List<CurrencyConversion> getAllWithin(String date, Long orgCode, Long accCode){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH);
        LocalDate date_1 = LocalDate.parse(date, formatter);
        return currencyConversionRepository.getAllWithinDates(date_1, orgCode, accCode);
    }

    public List<CurrencyConversion> search(String key){
        return currencyConversionRepository.globalSearch(key);
    }


}
