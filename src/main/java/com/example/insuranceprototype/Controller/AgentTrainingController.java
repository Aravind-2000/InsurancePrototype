package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.AgentTrainingDetails;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.AgentTrainingService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/traineeagent")
public class AgentTrainingController {

    @Autowired
    private ErrorService errorService;

    @Autowired
    private AgentTrainingService agentTrainingService;

    @Autowired
    private PermissionRepository permissionRepo;



    long programId = 13;


    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getAll(@PathVariable Long userid){

        String method = "get-all-trainee-agents";
        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(agentTrainingService.getAllTraineeAgents());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }


    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> get(@PathVariable Long userid, @PathVariable Long id){

        String method = "get-trainee-agent";
        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(agentTrainingService.getTraineeAgent(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> add(@PathVariable Long userid, @RequestBody AgentTrainingDetails traineeDetails){

        String method = "add-trainee";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(agentTrainingService.addTraineeAgent(traineeDetails));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }


    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> update(@PathVariable Long userid, @PathVariable Long id, @RequestBody AgentTrainingDetails traineeDetails){

        String method = "update-trainee-agent";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(agentTrainingService.updateTraineeAgentDetails(id, traineeDetails));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/softdelete/{id}/{userid}")
    public ResponseEntity<?> deactivate(@PathVariable Long userid, @PathVariable Long id){

        String method = "soft-delete-trainee-agent";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(agentTrainingService.deactivateTrainee(id));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @DeleteMapping("/{id}/{userid}")
    public ResponseEntity<?> delete(@PathVariable Long userid, @PathVariable Long id){

        String method = "hard-delete-trainee-agent";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(agentTrainingService.deleteTraineeDetails(id));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @GetMapping("/myTrainings/{agentId}/{userid}")
    public ResponseEntity<?> getMy(@PathVariable Long agentId, @PathVariable Long userid){

        String method = "get-my-trainings";

        if(!permissionRepo.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(agentTrainingService.getMyTrainings(agentId));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }
}
