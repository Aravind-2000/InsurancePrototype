package com.example.insuranceprototype.Proofs;

import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/proof")
public class ProofController{

    @Autowired
    ProofRepo proofRepo;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private PermissionRepository permissionRepo;

    int programId = 9;

    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getAll(@PathVariable Long userid){
        String method = "get-all-proof";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok( proofRepo.findAll());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/getActive/{userid}")
    public ResponseEntity<?> getAct(@PathVariable Long userid){
        String method = "get-all-active-proof";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok( proofRepo.getValidFlag());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> add(@RequestBody Proof proof, @PathVariable Long userid){

        String method = "add-proof";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            proof.setIsValid(1);
            proofRepo.save(proof);
            return ResponseEntity.ok(errorService.getErrorById( "ER001"));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/update/{id}/{userid}")
    public ResponseEntity<?> upd(@PathVariable Long id, @RequestBody Proof proof, @PathVariable Long userid){

        String method = "update-proof";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            Proof pf = proofRepo.getById(id);
            if(proof.getProofFile() != null){
                pf.setProofFile(proof.getProofFile());
            }
            if(proof.getProofName() != null){
                pf.setProofName(proof.getProofName());
            }
            if(proof.getClientID() != null){
                pf.setClientID(proof.getClientID());
            }
            if(proof.getProofPurpose() != null){
                pf.setProofPurpose(proof.getProofPurpose());
            }
            proofRepo.save(pf);
            return ResponseEntity.ok(errorService.getErrorById( "ER003 "));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/reinstate/{id}/{userid}")
    public  ResponseEntity<?> reins(@PathVariable Long id, @PathVariable Long userid){

        String method = "reinstate-proof";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            Proof proof = proofRepo.getById(id);
            proof.setIsValid(1);
            proofRepo.save(proof);
            return ResponseEntity.ok(errorService.getErrorById("ER003"));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/delete/{id}/{userid}")
    public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable Long userid){


        String method = "soft-delete-proof";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            Proof proof = proofRepo.getById(id);
            proof.setIsValid(-1);
            proofRepo.save(proof);
            return ResponseEntity.ok(errorService.getErrorById("ER003"));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @DeleteMapping("/hardDelete/{id}/{userid}")
    public ResponseEntity<?> deleteHard(@PathVariable Long id, @PathVariable Long userid){


        String method = "hard-delete-proof";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            Proof proof = proofRepo.getById(id);
            proofRepo.delete(proof);
            return ResponseEntity.ok("hard deleted");
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));

    }

}
