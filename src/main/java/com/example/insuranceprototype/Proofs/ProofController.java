package com.example.insuranceprototype.Proofs;

import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/proof")
public class ProofController{

    @Autowired
    ProofRepo proofRepo;

    @Autowired
    private ErrorService errorService;

    @GetMapping("/getall")
    public List<Proof> getAll(){
        return proofRepo.findAll();
    }

    @GetMapping("/getActive")
    public List<Proof> getAct(){
        return proofRepo.getValidFlag();
    }

    @PostMapping("/add")
    public String add(@RequestBody Proof proof){
        proof.setIsValid(1);
        proofRepo.save(proof);
//        return "added successfully";
        return errorService.getErrorById( "ER001");
    }

    @PatchMapping("/update/{id}")
    public String upd(@PathVariable Long id, @RequestBody Proof proof){
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
//        return "updated";
        return errorService.getErrorById( "ER003 ");
    }

    @PatchMapping("/reinstate/{id}")
    public String reins(@PathVariable Long id){
        Proof proof = proofRepo.getById(id);
        proof.setIsValid(1);
        proofRepo.save(proof);
        return "reinstated";
//        return errorService.getErrorbyid( " ");
    }

    @PatchMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        Proof proof = proofRepo.getById(id);
        proof.setIsValid(-1);
        proofRepo.save(proof);
//        return "deleted";
        return errorService.getErrorById( " ER003");
    }

    @DeleteMapping("/hardDelete/{id}")
    public String deletHard(@PathVariable Long id){
        Proof proof = proofRepo.getById(id);
        proofRepo.delete(proof);
        return "hard deleted";
//        return errorService.getErrorbyid( " ");
    }

}
