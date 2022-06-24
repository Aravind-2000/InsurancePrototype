package com.example.insuranceprototype.Controller;

import com.example.insuranceprototype.Entity.CurrencyConversion;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.CurrencyConversionService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencyconv")
@CrossOrigin
public class CurrencyConversionController {


    @Autowired
    private CurrencyConversionService currencyConversionService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private PermissionRepository permissionRepo;

    long programId = 17;


    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getAll(@PathVariable Long userid) {
        String method = "get-all-currencyconversion";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(currencyConversionService.getAllCurrConv());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> get(@PathVariable Long userid, @PathVariable Long id) {
        String method = "get-currencyconversion";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(currencyConversionService.getCurrConv(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @PostMapping("/add/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @RequestBody CurrencyConversion currencyConversion) {
        String method = "add-currencyconversion";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(currencyConversionService.addCurrConv(currencyConversion));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> update(@PathVariable Long userid,@PathVariable Long id, @RequestBody CurrencyConversion currencyConversion) {
        String method = "update-currencyconversion";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(currencyConversionService.updateCurrConv(id, currencyConversion));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @PatchMapping("/softdelete/{id}/{userid}")
    public ResponseEntity<?> softDelete(@PathVariable Long userid,@PathVariable Long id) {
        String method = "soft-delete-currencyconversion";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(currencyConversionService.softDeleteCurrConv(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @DeleteMapping("/harddelete/{id}/{userid}")
    public ResponseEntity<?> hardDelete(@PathVariable Long userid,@PathVariable Long id) {
        String method = "hard-delete-currencyconversion";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(currencyConversionService.hardDeleteCurrConv(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/exchangerate/{date}/{orgCode}/{accCode}/{seqNo}")
    public ResponseEntity<?> exchangeRates(@PathVariable String date, @PathVariable Long orgCode, @PathVariable Long accCode, @PathVariable Long seqNo){
        return ResponseEntity.ok(currencyConversionService.getExchangeRate(date, orgCode,accCode, seqNo));
    }

    @GetMapping("/getallwithin/{date}/{orgCode}/{accCode}")
    public ResponseEntity<?> getAllWithin(@PathVariable String date, @PathVariable Long orgCode, @PathVariable Long accCode){
        return ResponseEntity.ok(currencyConversionService.getAllWithin(date,orgCode,accCode));
    }


    @GetMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key){
        return ResponseEntity.ok(currencyConversionService.search(key));
    }
}
