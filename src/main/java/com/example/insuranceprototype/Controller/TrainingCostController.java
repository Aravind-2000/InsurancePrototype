package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.TrainingCost;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.TrainingCostService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/trainingcost")
@CrossOrigin
@RestController
public class TrainingCostController {


    @Autowired
    private TrainingCostService costService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private PermissionRepository permissionRepo;


    long programId = 15;


    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getAll(@PathVariable Long userid){

        String method = "get-all-cost";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(costService.getAllCosts());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> get(@PathVariable Long userid, @PathVariable Long id){

        String method = "get-cost";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(costService.getCost(id));
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @RequestBody TrainingCost cost){

        String method = "add-cost";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(costService.addTrainingCost(cost));
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> update(@PathVariable Long userid, @RequestBody TrainingCost cost, @PathVariable Long id){

        String method = "update-cost";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(costService.updateTrainingCost(id, cost));
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/softdelete/{id}/{userid}")
    public ResponseEntity<?> update(@PathVariable Long userid, @PathVariable Long id){

        String method = "soft-delete-cost";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(costService.deactivateCost(id));
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }
}
