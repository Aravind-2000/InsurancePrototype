package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.ClientMaintainPersonal;
import com.example.insuranceprototype.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping("/getall")
    public List<ClientMaintainPersonal> getall(){
        return clientService.getallClients();
    }

    @GetMapping("/{id}")
    public ClientMaintainPersonal getClient(@PathVariable Long id){
        return clientService.getClient(id);
    }

    @PostMapping("/add")
    public String addClient(@RequestBody ClientMaintainPersonal client){
        return clientService.addClient(client);
    }

    @PatchMapping("/{id}")
    public String updateClient(@PathVariable Long id, @RequestBody ClientMaintainPersonal clientMaintainPersonal){
        return clientService.updateClient(id, clientMaintainPersonal);
    }

}
