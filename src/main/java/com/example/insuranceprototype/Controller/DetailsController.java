package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.PersonalDetails;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.DetailsService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/candidates")
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @Autowired
    private PermissionRepository permissionRepo;

    @Autowired
    private ErrorService errorService;

    int programId = 8;

    @GetMapping("/getallDetails/{userid}")
    public ResponseEntity<?> getalldetails(@PathVariable Long userid){

        String method = "get-all-candidate";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok( detailsService.getAllDetails());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/get/{id}/{userid}")
    public ResponseEntity<?> getbyid(@PathVariable("id") Long id,@PathVariable Long userid){

        String method = "get-candidate";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok( detailsService.getByCandidateId(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/application/{id}")
    public PersonalDetails searchbyid(@PathVariable Long id){
        return detailsService.searchbyId(id);
    }

    @PostMapping(value = "/savedetails")
    public String savedetails(@RequestBody PersonalDetails details) throws IOException {
        return detailsService.saveDetails(details);
    }




    @PatchMapping("/update/{id}/{userid}")
    public ResponseEntity<?> updatedetails(@PathVariable Long id , @RequestBody PersonalDetails details,@PathVariable Long userid) throws FileNotFoundException {

        String method = "update-candidate";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok(detailsService.updateDetails(id, details));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @DeleteMapping("/delete/{id}/{userid}")
    public ResponseEntity<?> deletedetails(@PathVariable Long id,@PathVariable Long userid){
        String method = "hard-delete-candidate";

        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok(detailsService.deleteDetails(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/search/name/{name}")
    public List<PersonalDetails> getName(@PathVariable String name){
        return detailsService.getnamelike(name);
    }

    @GetMapping("/search/email/{val}")
    public List<PersonalDetails> getEmail(@PathVariable String val){
        return detailsService.getEmailLike(val);
    }

    @GetMapping("/search/proof/{val}")
    public List<PersonalDetails> getproof(@PathVariable String val){
        return detailsService.getProofLike(val);
    }

    @GetMapping("/search/candidatestatus/{val}")
    public List<PersonalDetails> getCurrentStatus(@PathVariable String val){
        return detailsService.getcurrentstatuslike(val);
    }

    @GetMapping("/search/{val}")
    public List<PersonalDetails> serchall(@PathVariable String val){
        return detailsService.searchAll(val);
    }

    @GetMapping("/passed/{val}")
    PersonalDetails searchPassed(@PathVariable String val){
        return detailsService.searchPassed(val);
    }
}
