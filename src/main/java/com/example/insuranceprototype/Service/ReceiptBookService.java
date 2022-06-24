package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.ReceiptBook;
import com.example.insuranceprototype.Repository.ReceiptBookRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptBookService {

    @Autowired
    private ReceiptBookRepository receiptBookRepository;

    @Autowired
    private ErrorService errorService;



    public List<ReceiptBook> getAllReceiptBook(){
        return receiptBookRepository.getAllValids();
    }

    public ReceiptBook getReceiptBook(Long id){
        return receiptBookRepository.findById(id).get();
    }

    public String addReceiptBook(ReceiptBook receiptBook){
        receiptBook.setValidFlag(1);
        receiptBookRepository.save(receiptBook);
        return errorService.getErrorById("ER001");
    }

    public String updateReceiptBook(Long id, ReceiptBook newOne){

        ReceiptBook oldOne = receiptBookRepository.getById(id);

        if(newOne.getAgentId() != null){
            oldOne.setAgentId(newOne.getAgentId());
        }

        if(newOne.getReceiptNo() != null){
            oldOne.setReceiptNo(newOne.getReceiptNo());
        }

        if(newOne.getReceiptDate() != null){
            oldOne.setReceiptDate(newOne.getReceiptDate());
        }

        if(newOne.getOriginalReceiptCurrencyId() != null){
            oldOne.setOriginalReceiptCurrencyId(newOne.getOriginalReceiptCurrencyId());
        }

        if(newOne.getReceiptMethod() != null){
            oldOne.setReceiptMethod(newOne.getReceiptMethod());
        }

        if(newOne.getTotalReceiptAmount() != null){
            oldOne.setTotalReceiptAmount(newOne.getTotalReceiptAmount());
        }

        receiptBookRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String softDeleteReceiptBook(Long id){
        ReceiptBook oldOne = receiptBookRepository.getById(id);
        oldOne.setValidFlag(-1);
        receiptBookRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String hardDeleteReceiptBook(Long id){
        receiptBookRepository.deleteById(id);
        return errorService.getErrorById("ER003");
    }

    public List<ReceiptBook> search(String key){
        return receiptBookRepository.globalSearch(key);
    }

}
