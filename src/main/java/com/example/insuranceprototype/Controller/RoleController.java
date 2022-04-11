package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.Role;
import com.example.insuranceprototype.Repository.RoleRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/role")
@RestController
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private ErrorService errorService;


    @GetMapping("/getall")
    public List<Role> getAllRoles(){
        return roleRepo.findAll();
    }

    @PostMapping("/add")
    public String addRoles(@RequestBody Role role){
        roleRepo.save(role);
        return errorService.getErrorById("ER001");
    }

}
