package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.AccountCodeTable;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.AccountCodeService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accountmaster")
@CrossOrigin
public class AccountCodeController {


    @Autowired
    private AccountCodeService accountMasterService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private PermissionRepository permissionRepo;

    long programId = 18;

    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getAll(@PathVariable Long userid) {
        String method = "get-all-accountmaster";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(accountMasterService.getAllAccMaster());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> get(@PathVariable Long userid, @PathVariable Long id) {
        String method = "get-accountmaster";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(accountMasterService.getAccMaster(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @RequestBody AccountCodeTable accountMaster) {
        String method = "add-accountmaster";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(accountMasterService.addAccMaster(accountMaster));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> update(@PathVariable Long userid, @RequestBody AccountCodeTable accountMaster, @PathVariable Long id) {
        String method = "update-accountmaster";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(accountMasterService.updateAccMaster(id,accountMaster));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/softdelete/{id}/{userid}")
    public ResponseEntity<?> softDelete(@PathVariable Long userid, @PathVariable Long id) {
        String method = "soft-delete-accountmaster";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(accountMasterService.softDeleteAccMaster(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @DeleteMapping("/hardDelete/{id}/{userid}")
    public ResponseEntity<?> hardDelete(@PathVariable Long userid, @PathVariable Long id) {
        String method = "hard-delete-accountmaster";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(accountMasterService.hardDeleteAccMaster(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key){
        return ResponseEntity.ok(accountMasterService.search(key));
    }

}
