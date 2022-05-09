package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.TrainingModule;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.TrainingModuleService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainingmodule")
@CrossOrigin
public class TrainingModuleController {

    @Autowired
    private TrainingModuleService moduleService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private PermissionRepository permissionRepo;


    long programId = 14;

    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getAll(@PathVariable Long userid){

        String method = "get-all-modules";

        if(!permissionRepo.isMethodPresent(userid, programId,method).isEmpty()){
            return ResponseEntity.ok(moduleService.getAllModules());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("{id}/{userid}")
    public ResponseEntity<?> get(@PathVariable Long userid,@PathVariable Long id ){

        String method = "get-module";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(moduleService.getModule(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PostMapping("add/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @RequestBody TrainingModule module){

        String method = "add-module";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(moduleService.addModule(module));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("{id}/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @PathVariable Long id, @RequestBody TrainingModule module){

        String method = "update-module";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(moduleService.updateModule(id, module));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("softdelete/{id}/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @PathVariable Long id){

        String method = "soft-delete-module";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(moduleService.deactivateModule(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

}
