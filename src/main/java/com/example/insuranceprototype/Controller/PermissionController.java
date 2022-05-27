package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.Permissions;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ErrorService errorService;

    long programId = 11;

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> addPermission(@RequestBody Permissions permissions, @PathVariable Long userid){

        String method = "add-permission";
        if(!permissionRepository.isMethodPresent(userid, programId, method).isEmpty()){
            permissionRepository.save(permissions);
            return ResponseEntity.ok(permissionRepository.findAll());
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @GetMapping("/methods")
    public List<String> getAllMethods(){
        return permissionRepository.allPermissionMethods();
    }
}
