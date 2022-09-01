package com.example.insuranceprototype.Controller;


import com.example.insuranceprototype.Entity.ClientAddressTable;
import com.example.insuranceprototype.Repository.PermissionRepository;
import com.example.insuranceprototype.Service.ClientAddressService;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/address")
@RestController
@CrossOrigin
public class AddressController {

    @Autowired
    private ClientAddressService addressService;

    @Autowired
    private PermissionRepository permissionRepo;

    @Autowired
    private ErrorService errorService;

    int programId = 3;


    @GetMapping("/getall/{userid}")
    public ResponseEntity<?> getall(@PathVariable Long userid){
        String method = "get-all-address";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok(addressService.getallAddress());
        }
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
    }

    @GetMapping("{id}/{userid}")
    public ResponseEntity<?> getaddress(@PathVariable Long id, @PathVariable Long userid){
        String method = "get-address";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok(addressService.getAddress(id));
        }
        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> addaddress(@RequestBody ClientAddressTable address, @PathVariable Long userid){
        String method ="add-address";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok(addressService.addAddress(address));
        }

        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @PatchMapping("/{id}/{userid}")
    public ResponseEntity<?> updateaddress(@PathVariable Long id,@RequestBody ClientAddressTable address , @PathVariable Long userid){
        String method = "update-address";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok(addressService.update(id, address));
        }

        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }
    @PatchMapping("/delete/{id}/{userid}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id, @PathVariable Long userid ){
        String method = "soft-delete-address";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok(addressService.deleteAddress(id));
        }

        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @DeleteMapping("/{id}/{userid}")
    public ResponseEntity<?> harddelete(@PathVariable Long id, @PathVariable Long userid){
        String method = "hard-delete-address";
        if(!permissionRepo.isMethodPresent(userid, (long)programId, method).isEmpty()){
            return ResponseEntity.ok(addressService.hardDelete(id));
        }

        return ResponseEntity.ok(errorService.getErrorById("ER007"));
    }

    @GetMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key){
        return ResponseEntity.ok(addressService.globalSearch(key));
    }
}
