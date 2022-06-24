package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.SubAccountTable;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.SubAccountService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subaccount")
@CrossOrigin
public class SubAccountController {

    @Autowired
    private SubAccountService subAccountService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private PermissionRepository permissionRepo;

    long programId = 23;

    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getAll(@PathVariable Long userid) {
        String method = "get-all-subaccount";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(subAccountService.getAllSubCodes());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> get(@PathVariable Long userid, @PathVariable Long id) {
        String method = "get-subaccount";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(subAccountService.getSubCode(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @PostMapping("/add/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @RequestBody SubAccountTable subAccount) {
        String method = "add-subaccount";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(subAccountService.addSubCode(subAccount));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> update(@PathVariable Long userid, @RequestBody SubAccountTable subAccount, @PathVariable Long id) {
        String method = "update-subaccount";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(subAccountService.updateSubCode(id,subAccount));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/softdelete/{id}/{userid}")
    public ResponseEntity<?> softDelete(@PathVariable Long userid, @PathVariable Long id) {
        String method = "soft-delete-subaccount";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(subAccountService.softDeleteSubCode(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @DeleteMapping("/hardDelete/{id}/{userid}")
    public ResponseEntity<?> hardDelete(@PathVariable Long userid, @PathVariable Long id) {
        String method = "hard-delete-subaccount";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(subAccountService.hardDeleteSubCode(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key){
        return ResponseEntity.ok(subAccountService.search(key));
    }



}
