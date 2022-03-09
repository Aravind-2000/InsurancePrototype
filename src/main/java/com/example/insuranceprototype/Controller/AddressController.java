package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.ClientAddressTable;
import com.example.insuranceprototype.Service.ClientAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/address")
@RestController
@CrossOrigin
public class AddressController {

    @Autowired
    private ClientAddressService addressService;


    @GetMapping("/getall")
    public List<ClientAddressTable> getall(){
        return  addressService.getallAddress();
    }

    @GetMapping("{id}")
    public ClientAddressTable getaddress(@PathVariable Long id){
        return addressService.getAddress(id);
    }

    @PostMapping("/add")
    public String addaddress(@RequestBody ClientAddressTable address){
        return addressService.addAddress(address);
    }

    @PatchMapping("/{id}")
    public String updateaddress(@PathVariable Long id,@RequestBody ClientAddressTable address ){
        return addressService.update(id, address);
    }
    @PatchMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Long id){
        return addressService.deleteAddress(id);
    }

}
