package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.CurrencyCode;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.CurrencyCodeService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencycode")
@CrossOrigin
public class CurrencyCodeController {

    @Autowired
    private CurrencyCodeService currencyCodeService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private PermissionRepository permissionRepo;

    long programId = 16;

    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getAll(@PathVariable Long userid){
        String method = "get-all-currency";
        if(!permissionRepo.isMethodPresent(userid,programId, method).isEmpty()){
            return ResponseEntity.ok(currencyCodeService.getAllCurrCodes());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> get(@PathVariable Long id, @PathVariable Long userid){
        String method = "get-currency";
        if(!permissionRepo.isMethodPresent(userid,programId,method).isEmpty()){
            return ResponseEntity.ok(currencyCodeService.getCurrCode(id));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @RequestBody CurrencyCode currencyCode){
        String method = "add-currency";
        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(currencyCodeService.addCurrCode(currencyCode));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> update(@PathVariable Long id, @PathVariable Long userid, @RequestBody CurrencyCode currencyCode){
        String method = "update-currency";
        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(currencyCodeService.updateCurrCode(id, currencyCode));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/softdelete/{id}/{userid}")
    public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userid){
        String method = "soft-delete-currency";
        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(currencyCodeService.softDeleteCurrCode(id));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @DeleteMapping("/harddelete/{id}/{userid}")
    public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userid){
        String method = "hard-delete-currency";
        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(currencyCodeService.hardDeleteCurrCode(id));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @GetMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key){
        return ResponseEntity.ok(currencyCodeService.search(key));
    }

}
