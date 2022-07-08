package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.PolicyCover;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.PolicyCoverService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policycover")
@CrossOrigin
public class PolicyCoverController {


    @Autowired
    private PolicyCoverService policyCoverService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private PermissionRepository permissionRepo;

    long programId = 25;



    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getAll(@PathVariable Long userid) {
        String method = "get-all-policycover";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(policyCoverService.getAllPolicyCover());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }


    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> get(@PathVariable Long userid, @PathVariable Long id) {
        String method = "get-policycover";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(policyCoverService.getPolicyCover(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @RequestBody PolicyCover policyCover) {
        String method = "add-policycover";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(policyCoverService.addPolicyCover(policyCover));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PostMapping("/clone/{userid}")
    public ResponseEntity<?> clone(@PathVariable Long userid, @RequestBody PolicyCover policyCover1) {
        String method = "clone-policycover";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(policyCoverService.clonePolicyCover(policyCover1));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> update(@PathVariable Long userid, @PathVariable Long id,@RequestBody PolicyCover policyCover ) {
        String method = "update-policycover";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(policyCoverService.updatePolicyCover(id, policyCover));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }


    @PatchMapping("softdelete/{id}/{userid}")
    public ResponseEntity<?> softDelete(@PathVariable Long userid, @PathVariable Long id) {
        String method = "soft-delete-policycover";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(policyCoverService.softDeletePolicyCover(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key) {
        return ResponseEntity.ok(policyCoverService.search(key));
    }

    @DeleteMapping("/{id}/{userid}")
    public ResponseEntity<?> hardDelete(@PathVariable Long userid, @PathVariable Long id) {
        String method = "hard-delete-policycover";
        if (!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()) {
            return ResponseEntity.ok(policyCoverService.hardDeletePolicyCover(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/header/{id}")
    public ResponseEntity<?> header(@PathVariable Long id){
        return ResponseEntity.ok(policyCoverService.getByHeader(id));
    }

}
