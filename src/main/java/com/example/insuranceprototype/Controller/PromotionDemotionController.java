package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.Promotion_Demotion_Details;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.PromotionDemotionService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@RequestMapping("/promote")
@CrossOrigin
@RestController
public class PromotionDemotionController {

    @Autowired
    private PromotionDemotionService promotionDemotionService;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private PermissionRepository permissionRepository;


    long programId = 10;

    @PostMapping("/add/{userid}")
    private ResponseEntity<?> doPromote(@RequestBody Promotion_Demotion_Details details, @PathVariable Long userid) throws IOException {

        String method = "do-promote-demote";
        if(!permissionRepository.isMethodPresent(userid, programId, method).isEmpty()){
            return ResponseEntity.ok(promotionDemotionService.doPromotionOrDemotion(details, programId, method));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

}
