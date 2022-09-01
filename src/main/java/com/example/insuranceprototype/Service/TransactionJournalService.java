package com.example.insuranceprototype.Service;

import com.example.insuranceprototype.Entity.ReceiptBook;
import com.example.insuranceprototype.Entity.TransactionCode;
import com.example.insuranceprototype.Entity.TransactionJournal;
import com.example.insuranceprototype.Repository.*;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TransactionJournalService {


    @Autowired
    private TransactionJournalRepository transactionJournalRepository;

    @Autowired
    private TransactionCodeRepository transactionCodeRepository;
    @Autowired
    private CurrencyConversionRepository currencyConversionRepository;
    @Autowired
    private ReceiptBookRepository receiptBookRepository;
    @Autowired
    private ErrorService errorService;


    public List<TransactionJournal> getAllTransJournal(){
        return transactionJournalRepository.getAllValids();
    }

    public TransactionJournal getTransJournal(Long id){
        return transactionJournalRepository.getById(id);
    }

    public String addTransJournal(TransactionJournal transactionJournal){
        transactionJournal.setValidFlag(1);

        //Adding this amount to the total amount in its specific Receipt Book
        ReceiptBook receiptBook = receiptBookRepository.getById(transactionJournal.getReceiptId());
        if(receiptBook.getTotalReceiptAmount() == null){
            receiptBook.setTotalReceiptAmount(transactionJournal.getOriginalAmount());
        }
        if(receiptBook.getTotalReceiptAmount() != null){
            receiptBook.setTotalReceiptAmount(receiptBook.getTotalReceiptAmount() + transactionJournal.getOriginalAmount());
        }

//        Assigning account Sign
//        AccountingRule accountingRule = accountingRuleRepository.getById(transactionJournal.getAccountingRuleId());
//        transactionJournal.setAccountSign(accountingRule.getAccountSign());

        // Exchange Rate Calculation
        TransactionCode transactionCode = transactionCodeRepository.getById(transactionJournal.getTransactionId());
        Double exchangeRate = currencyConversionRepository.getExchangeRate(transactionCode.getTransactionDate().toLocalDate(), transactionJournal.getOriginalCurrencyId(), transactionJournal.getAccountCurrencyId(), transactionJournal.getSequenceNo());
        transactionJournal.setAccountAmount(transactionJournal.getOriginalAmount() * exchangeRate);


        transactionJournalRepository.save(transactionJournal);
        return errorService.getErrorById("ER001");
    }

    public String updateTransJournal(Long id, TransactionJournal newOne){

        TransactionJournal oldOne = transactionJournalRepository.getById(id);

        if(newOne.getAgentId() != null){
            oldOne.setAgentId(newOne.getAgentId());
        }

        if(newOne.getAccountingRuleId() != null){
            oldOne.setAccountingRuleId(newOne.getAccountingRuleId());
        }

        if(newOne.getReceiptId() != null){
            oldOne.setReceiptId(newOne.getReceiptId());
        }


        if(newOne.getAccountSign() != null){
            oldOne.setAccountSign(newOne.getAccountSign());
        }

        if(newOne.getOriginalAmount() != null){
            oldOne.setOriginalAmount(newOne.getOriginalAmount());
        }

        if(newOne.getTransactionId() != null){
            oldOne.setTransactionId(newOne.getTransactionId());
        }

        if(newOne.getOriginalCurrencyId() != null){
            oldOne.setOriginalCurrencyId(newOne.getOriginalCurrencyId());
        }

        if(newOne.getAccountCurrencyId() != null){
            oldOne.setAccountCurrencyId(newOne.getAccountCurrencyId());
        }

        if(newOne.getSequenceNo() != null){
            oldOne.setSequenceNo(newOne.getSequenceNo());
        }

        TransactionCode transactionCode = transactionCodeRepository.getById(newOne.getTransactionId());
        Double exchangeRate = currencyConversionRepository.getExchangeRate(transactionCode.getTransactionDate().toLocalDate(), newOne.getOriginalCurrencyId(), newOne.getAccountCurrencyId(), newOne.getSequenceNo());
        oldOne.setAccountAmount(newOne.getOriginalAmount() * exchangeRate);

        if(newOne.getAccountAmount() != null){
            oldOne.setAccountAmount(newOne.getAccountAmount());
        }
        transactionJournalRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String softDeleteTransJournal(Long id){
        TransactionJournal oldOne = transactionJournalRepository.getById(id);
        oldOne.setValidFlag(-1);
        transactionJournalRepository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String hardDeleteTransJournal(Long id){
        transactionJournalRepository.deleteById(id);
        return errorService.getErrorById("ER003");
    }

    public List<TransactionJournal> search(String key){
        return transactionJournalRepository.globalSearch(key);
    }

    public List<TransactionJournal> basedUponAgent(Long agent){
        return transactionJournalRepository.basedUponAgentId(agent);
    }
}
