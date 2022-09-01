package com.example.insuranceprototype.Controller;

import com.example.insuranceprototype.Entity.OfficeStructure;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.OfficeStructureService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("officestructure")
public class OfficeStructureController {

    @Autowired
    private OfficeStructureService officeStructureService;

    @Autowired
    private PermissionRepository permissionRepo;

    @Autowired
    private ErrorService errorService;

    int programId = 6;

    @GetMapping("/getall/{userid}")
    public  ResponseEntity<?> get(@PathVariable Long userid){

        String method = "get-all-office";
            if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
                return ResponseEntity.ok(  officeStructureService.getAllActive());
            }
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/getbranch/{id}/{userid}")
    public  ResponseEntity<?> getBR(@PathVariable Long id,@PathVariable Long userid){

        String method = "get-office";
            if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
                return ResponseEntity.ok(  officeStructureService.getUpLevel(id));
            }
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));

    }

    @GetMapping("/getofficelevel/{id}")
    public List<OfficeStructure> getOL(@PathVariable Long id){
        return officeStructureService.getByOfficeLevel(id);
    }

    @GetMapping("/getuplevel/{id}/{userid}")
    public ResponseEntity<?> getUP(@PathVariable Long id, @PathVariable Long userid){
        String method = "get-all-downlevel-offices";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok(  officeStructureService.getById(id));
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @PostMapping("/add/{userid}")
    public  ResponseEntity<?> add(@RequestBody OfficeStructure officeStructure,@PathVariable Long userid){

        String method = "add-office";
            if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
                return ResponseEntity.ok(officeStructureService.addOffice(officeStructure));
            }
            return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> updateOffice(@PathVariable Long id, @RequestBody OfficeStructure officeStructure, @PathVariable Long userid){

        String method = "update-office";
            if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
                return ResponseEntity.ok( officeStructureService.updateOffice(id, officeStructure));
            }
            return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/softdelete/{id}/{userid}")
    public  ResponseEntity<?> softDelete(@PathVariable Long id,@PathVariable Long userid){

        String method = "soft-delete-office";

            if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
                return ResponseEntity.ok(  officeStructureService.deactivateCompany(id));
            }
            return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key){
        return ResponseEntity.ok(officeStructureService.globalSearch(key));
    }
}

