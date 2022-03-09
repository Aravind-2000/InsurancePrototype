package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.PersonalDetails;
import com.example.insuranceprototype.Service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
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

    @GetMapping("/application/{id}")
    public PersonalDetails searchbyid(@PathVariable Long id){
        return detailsService.searchbyId(id);
    }

    @PostMapping(value = "/savedetails")
    public String savedetails(@RequestBody PersonalDetails details) throws IOException {
        return detailsService.saveDetails(details);
    }

    @PatchMapping("/update/{id}")
    public String updatedetails(@PathVariable Long id , @RequestBody PersonalDetails details) throws FileNotFoundException {
        return detailsService.updateDetails(id, details);
    }

    @DeleteMapping("/delete/{id}")
    public String deletedetails(@PathVariable Long id){
        return detailsService.deleteDetails(id);
    }

    @GetMapping("/search/name/{name}")
    public List<PersonalDetails> getName(@PathVariable String name){
        return detailsService.getnamelike(name);
    }

    @GetMapping("/search/email/{val}")
    public List<PersonalDetails> getEmail(@PathVariable String val){
        return detailsService.getEmailLike(val);
    }

    @GetMapping("/search/proof/{val}")
    public List<PersonalDetails> getproof(@PathVariable String val){
        return detailsService.getProofLike(val);
    }

    @GetMapping("/search/candidatestatus/{val}")
    public List<PersonalDetails> getCurrentStatus(@PathVariable String val){
        return detailsService.getcurrentstatuslike(val);
    }

    @GetMapping("/search/{val}")
    public List<PersonalDetails> serchall(@PathVariable String val){
        return detailsService.searchAll(val);
    }

    @GetMapping("/passed/{val}")
    PersonalDetails searchPassed(@PathVariable String val){
        return detailsService.searchPassed(val);
    }
}
