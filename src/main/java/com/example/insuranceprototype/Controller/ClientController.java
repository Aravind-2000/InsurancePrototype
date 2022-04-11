package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Auth.repository.UserRepository;
import com.example.insuranceprototype.Entity.ClientMaintainPersonal;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.ClientService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PermissionRepository permissionRepo;

    @Autowired
    private ErrorService errorService;

    int programId = 2;


    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getall(@PathVariable Long userid){

        String method = "get-all-client";
        if(userRepo.existsById(userid)){
            if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
                return ResponseEntity.ok(clientService.getallClients());
            }
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER008"));

    }

    @GetMapping("/{id}/{userid}")
    public ResponseEntity<?> getClient(@PathVariable Long id, @PathVariable Long userid){

        String method = "get-client";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok(clientService.getClient(id));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("/search/{val}")
    public List<ClientMaintainPersonal> searchClient(@PathVariable String val){
        return clientService.search(val);
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> addClient(@RequestBody ClientMaintainPersonal client , @PathVariable Long userid){

        String method = "add-client";
        if(userRepo.existsById(userid)){
            if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
                return ResponseEntity.ok(clientService.addClient(client));
            }
            return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER008"));
    }

    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @PathVariable long userid, @RequestBody ClientMaintainPersonal clientMaintainPersonal){

        String method = "update-client";
        if(userRepo.existsById(userid)){
            if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
                return ResponseEntity.ok(clientService.updateClient(id, clientMaintainPersonal));
            }
            return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER008"));
    }

    @PatchMapping("/del/{id}/{userid}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @PathVariable Long userid){

        String method = "soft-delete-client";
        if(userRepo.existsById(userid)){
            if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
                return ResponseEntity.ok( clientService.deleteClient(id));
            }
            return ResponseEntity.ok().body(errorService.getErrorById("ER007"));
        }
        return ResponseEntity.ok().body(errorService.getErrorById("ER008"));
    }
}
