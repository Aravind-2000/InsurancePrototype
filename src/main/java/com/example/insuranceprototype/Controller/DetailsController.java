package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.PersonalDetails;
import com.example.insuranceprototype.Service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidates")
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @GetMapping("/getallDetails")
    public List<PersonalDetails> getalldetails(){
        return detailsService.getAllDetails();
    }

    @GetMapping("/get/{id}")
    public Optional<PersonalDetails> getbyid(@PathVariable("id") Long id){
        return detailsService.getByCandidateId(id);
    }

    @PostMapping("/savedetails")
    public String savedetails(@RequestBody PersonalDetails details){
        return detailsService.saveDetails(details);
    }

    @PatchMapping("/update/{id}")
    public String updatedetails(@PathVariable Long id , @RequestBody PersonalDetails details){
        return detailsService.updateDetails(id, details);
    }

    @DeleteMapping("/delete/{id}")
    public String deletedetails(@PathVariable Long id){
        return detailsService.deleteDetails(id);
    }
}
