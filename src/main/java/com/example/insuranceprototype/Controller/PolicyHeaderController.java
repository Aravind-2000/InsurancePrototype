package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.PolicyHeader;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.PolicyHeaderService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policyheader")
@CrossOrigin
public class PolicyHeaderController {


    @Autowired
    private PolicyHeaderService policyHeaderService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private PermissionRepository permissionRepo;

    long programId = 24;



    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getAll(@PathVariable Long userid) {
        String method = "get-all-policyheader";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(policyHeaderService.getAllPolicyHeader());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }


    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> get(@PathVariable Long userid, @PathVariable Long id) {
        String method = "get-policyheader";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(policyHeaderService.getPolicyHeader(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @PostMapping("/add/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @RequestBody PolicyHeader policyHeader) {
        String method = "add-policyheader";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(policyHeaderService.addPolicyHeader(policyHeader));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> update(@PathVariable Long userid,@PathVariable Long id, @RequestBody PolicyHeader policyHeader) {
        String method = "update-policyheader";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(policyHeaderService.updatePolicyHeader(id,policyHeader));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @PatchMapping("/softdelete/{id}/{userid}")
    public ResponseEntity<?> softDelete(@PathVariable Long userid,@PathVariable Long id) {
        String method = "soft-delete-policyheader";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(policyHeaderService.softDeletePolicyHeader(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @DeleteMapping("/harddelete/{id}/{userid}")
    public ResponseEntity<?> hardDelete(@PathVariable Long userid,@PathVariable Long id) {
        String method = "hard-delete-policyheader";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(policyHeaderService.hardDeletePolicyHeader(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @GetMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key){
        return ResponseEntity.ok(policyHeaderService.search(key));
    }

}
