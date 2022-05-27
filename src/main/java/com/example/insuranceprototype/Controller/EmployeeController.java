package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.Employee;
import com.example.insuranceprototype.Entity.PersonalDetails;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.EmployeeService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @Autowired
    private PermissionRepository permissionRepo;

    @Autowired
    private ErrorService errorService;

    int programId = 7;

    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getallemp(@PathVariable Long userid){

        String method = "get-all-employee";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok( empService.getallEmployees());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> getempbyid(@PathVariable Long id,@PathVariable Long userid){

        String method = "get-employee";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok(  empService.getempById(id));
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> addemp(@RequestBody Employee employee,@PathVariable Long userid){

        String method = "add-employee";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok( empService.addEmployee(employee));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> updateempdetails(@PathVariable Long id, @RequestBody Employee employee,@PathVariable Long userid){
        String method = "update-employee";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok( empService.updateEmpDetails(id, employee));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/today/{id}")
    public List<PersonalDetails> getTodaysCandidates(@PathVariable Long id){
        return empService.todaysInterview(id);
    }

    @GetMapping("/upcoming/{id}")
    public List<PersonalDetails> getUpcomingCandidates(@PathVariable Long id){
        return empService.upcomingCandidates(id);
    }

    @GetMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key){
        return ResponseEntity.ok(empService.globalSearch(key));
    }

    @GetMapping("/passed/{id}")
    public List<PersonalDetails> passedAssignedCandidates(@PathVariable Long id){
        return empService.getPassedAssignedCandidates(id);
    }


    @GetMapping("/getpassed/{id}")
    public long getPassedCount(@PathVariable Long id){
        return empService.getPassed(id);
    }

    @GetMapping("/getfailed/{id}")
    public long getFailedCount(@PathVariable Long id){
        return empService.getFailed(id);
    }
}
