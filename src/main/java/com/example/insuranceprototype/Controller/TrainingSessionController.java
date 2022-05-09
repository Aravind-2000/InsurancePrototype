package com.example.insuranceprototype.Controller;

import com.example.insuranceprototype.Entity.TrainingSession;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.TrainingSessionService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/training")
@CrossOrigin
public class TrainingSessionController {


    @Autowired
    private ErrorService errorService;

    @Autowired
    private TrainingSessionService trainingService;

    @Autowired
    private PermissionRepository permissionRepo;



    long programId = 12;


    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getAll(@PathVariable Long userid){

        String method = "get-all-trainings";
        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(trainingService.getAllTrainings());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }


    @GetMapping("{id}/{userid}")
    public ResponseEntity<?> get(@PathVariable Long id, @PathVariable Long userid){

        String method = "get-training";
        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(trainingService.getTraining(id));
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @RequestBody TrainingSession training){

        String method = "add-training";
        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(trainingService.addTraining(training));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> update(@PathVariable Long userid, @PathVariable Long id, @RequestBody TrainingSession training){

        String method = "update-training";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(trainingService.updateTraining(id, training));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/softdelete/{id}/{userid}")
    public ResponseEntity<?> deactivate(@PathVariable Long userid, @PathVariable Long id){

        String method = "soft-delete-training";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(trainingService.deactivateTraining(id));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @DeleteMapping("/{id}/{userid}")
    public ResponseEntity<?> delete(@PathVariable Long userid, @PathVariable Long id){

        String method = "hard-delete-training";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(trainingService.deleteTraining(id));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @GetMapping("/datevalidation/{startdate}/{enddate}")
    public ResponseEntity<?> validation(@PathVariable String startdate, @PathVariable String enddate){
        return ResponseEntity.ok(trainingService.dateValidation(startdate, enddate));
    }

}
