package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.Quantitative;
import com.example.insuranceprototype.Service.QuantitativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/quants")
public class QuantitativeController {


    @Autowired
    private QuantitativeService quantitativeService;

    @GetMapping("/getall")
    public List<Quantitative> getallquants(){
        return quantitativeService.getAllQuantitative();
    }

    @GetMapping("/{id}")
    public Optional<Quantitative> getquantsbyid(@PathVariable(value = "id") Long id){
        return quantitativeService.getOneQuantitative(id);
    }

    @PostMapping("/save")
    public String savequants(@RequestBody Quantitative quantitative){
        return quantitativeService.saveDetails(quantitative);
    }
    
    @PatchMapping("/{id}")
    public Quantitative updatequants(@PathVariable(value = "id") Long id, @RequestBody Quantitative quantitative){
        return quantitativeService.updateDetails(id, quantitative);
    }

}
