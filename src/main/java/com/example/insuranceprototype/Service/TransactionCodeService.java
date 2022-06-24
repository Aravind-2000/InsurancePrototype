package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.TransactionCode;
import com.example.insuranceprototype.Repository.TransactionCodeRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionCodeService {


    @Autowired
    private TransactionCodeRepository transactionCodeRepository;

    @Autowired
    private ErrorService errorService;


    public List<TransactionCode> getAllTransactionCode(){
        return transactionCodeRepository.getALlValids();
    }

    public TransactionCode getTransactionCode(Long id){
        return transactionCodeRepository.findById(id).get();
    }

    public String addTransactionCode(TransactionCode transactionCode){
        transactionCode.setValidFlag(1);
        transactionCode.setTransactionDate(LocalDateTime.now());
        transactionCodeRepository.save(transactionCode);
        return errorService.getErrorById("ER001");
    }

    public String updateTransactionCode(Long id, TransactionCode newOne){

        TransactionCode oldOne = transactionCodeRepository.getById(id);

        if(newOne.getTransactionCode() != null){
            oldOne.setTransactionCode(newOne.getTransactionCode());
        }

        if(newOne.getTransactionDate() != null){
            oldOne.setTransactionDate(newOne.getTransactionDate());
        }

        if(newOne.getTransactionDesc() != null){
            oldOne.setTransactionDesc(newOne.getTransactionDesc());
        }
        transactionCodeRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String softDelete(Long id){
        TransactionCode oldOne = transactionCodeRepository.getById(id);
        oldOne.setValidFlag(-1);
        transactionCodeRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String hardDelete(Long id){
        transactionCodeRepository.deleteById(id);
        return errorService.getErrorById("ER003");
    }

    public List<TransactionCode> search(String key){
        return transactionCodeRepository.globalSearch(key);
    }
}
