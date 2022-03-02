package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.BankAccount;
import com.example.insuranceprototype.Service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bank")
@CrossOrigin
@RestController
public class BankAccountController {

    @Autowired
    private BankAccountService accService;

    @GetMapping("/getall")
    public List<BankAccount> viewAllBank(){
        return accService.getallbankacc();
    }

    @GetMapping("/{id}")
    public BankAccount viewBank(@PathVariable Long id){
        return accService.getbankacc(id);
    }

    @PostMapping("/add")
    public String addBank(@RequestBody BankAccount account){
        return accService.addBankacc(account);
    }

    @PatchMapping("/{id}")
    public String updateBank(@PathVariable Long id, @RequestBody BankAccount account){
        return accService.updatebankacc(id, account);
    }

}
