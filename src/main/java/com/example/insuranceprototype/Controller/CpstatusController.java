package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Repository.CpstatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cpstatus")
@CrossOrigin
public class CpstatusController {

    @Autowired
    private CpstatusRepository cpstatusRepository;



    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(cpstatusRepository.getAllValids());
    }
}
