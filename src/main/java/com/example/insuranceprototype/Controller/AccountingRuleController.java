package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.AccountingRule;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.AccountingRuleService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accountrule")
@CrossOrigin
public class AccountingRuleController {


    @Autowired
    private AccountingRuleService accountingRuleService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private PermissionRepository permissionRepo;

    long programId = 19;



    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getAll(@PathVariable Long userid) {
        String method = "get-all-accountrule";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(accountingRuleService.getAllAccRule());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }


    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> get(@PathVariable Long userid, @PathVariable Long id) {
        String method = "get-accountrule";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(accountingRuleService.getAccRule(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @RequestBody AccountingRule accountingRule) {
        String method = "add-accountrule";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(accountingRuleService.addAccRule(accountingRule));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> update(@PathVariable Long userid, @RequestBody AccountingRule accountingRule, @PathVariable Long id) {
        String method = "update-accountrule";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(accountingRuleService.updateAccRule(id, accountingRule));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @PatchMapping("/softdelete/{id}/{userid}")
    public ResponseEntity<?> softDelete(@PathVariable Long userid, @PathVariable Long id) {
        String method = "soft-delete-accountrule";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(accountingRuleService.softDeleteAccRule(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @DeleteMapping("/harddelete/{id}/{userid}")
    public ResponseEntity<?> hardDelete(@PathVariable Long userid, @PathVariable Long id) {
        String method = "hard-delete-accountrule";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(accountingRuleService.hardDeleteAccRule(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @GetMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key){
        return ResponseEntity.ok(accountingRuleService.search(key));
    }


}
