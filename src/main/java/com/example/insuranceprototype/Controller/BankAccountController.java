package com.example.insuranceprototype.Controller;

import com.example.insuranceprototype.Entity.BankAccount;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.BankAccountService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bank")
@CrossOrigin
@RestController
public class BankAccountController {

    @Autowired
    private BankAccountService accService;

    @Autowired
    private PermissionRepository permissionRepo;

    @Autowired
    private ErrorService errorService;

    int programId = 4;

    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> viewAllBank(@PathVariable Long userid){
        String method = "get-all-bank";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok(accService.getallbankacc());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> viewBank(@PathVariable Long id, @PathVariable Long userid){
        String method = "get-bank";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok( accService.getbankacc(id));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @GetMapping("/search/{val}")
    public List<BankAccount> search(@PathVariable String val){
        return accService.search(val);
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> addBank(@RequestBody BankAccount account, @PathVariable Long userid){
        String method = "add-bank";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok( accService.addBankacc(account));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> updateBank(@PathVariable Long id, @RequestBody BankAccount account, @PathVariable Long userid){
        String method = "update-bank";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok( accService.updatebankacc(id, account));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/delete/{id}/{userid}")
    public ResponseEntity<?> deleteBank(@PathVariable Long id, @PathVariable Long userid){
        String method = "soft-delete-bank";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok( accService.tempDeleteBank(id));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

}
