package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Auth.repository.UserRepository;
import com.example.insuranceprototype.Entity.AgentAppointmentDetails;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.AgentAppoinmentService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agent")
@CrossOrigin
public class AgentController {

    @Autowired
    private AgentAppoinmentService agentService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PermissionRepository permissionRepo;

    @Autowired
    private ErrorService errorService;

    int programId = 1;



    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getallagents(@PathVariable Long userid){
        String method = "get-all-agent";
        if(userRepo.existsById(userid)){
            if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
                return ResponseEntity.ok(agentService.getAllAgent());
            }
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER008"));
    }

    @GetMapping("/getallinvalids")
    public List<AgentAppointmentDetails> getallinvalidagents(){
        return agentService.getInvalidAgents();
    }

    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> getAgent(@PathVariable Long id, @PathVariable Long userid){

        String method = "get-agent";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok(agentService.getAgentById(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/search/{val}")
    public List<AgentAppointmentDetails> globalSearch(@PathVariable String val){
        return agentService.search(val);
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> addagent(@RequestBody AgentAppointmentDetails agent, @PathVariable Long userid){
        String method = "add-agent";
        if(userRepo.existsById(userid)) {
            if (!permissionRepo.isMethodPresent(userid, (long) programId, method).isEmpty()) {
                return ResponseEntity.ok(agentService.addAgent(agent));
            }
            return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER008"));
    }

    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> updateAgent(@PathVariable Long id, @RequestBody AgentAppointmentDetails agent, @PathVariable Long userid ){

        String method = "update-agent";

        if(userRepo.existsById(userid)) {
            if (!permissionRepo.isMethodPresent(userid, (long) programId, method).isEmpty()) {
                return ResponseEntity.ok(agentService.updateAgent(id, agent));
            }
            return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER008"));
    }

    @PatchMapping("/delete/{id}/{userid}")
    public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userid){

        String method = "soft-delete-agent";

        if(userRepo.existsById(userid)) {
            if (!permissionRepo.isMethodPresent(userid, (long) programId, method).isEmpty()) {
                return ResponseEntity.ok(agentService.delete(id));
            }
            return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER008"));
    }
}
