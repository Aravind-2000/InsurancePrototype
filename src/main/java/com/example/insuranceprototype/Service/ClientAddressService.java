package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.ClientAddressTable;
import com.example.insuranceprototype.Repository.ClientAddressRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ClientAddressService {

    @Autowired
    private ClientAddressRepository addressRepo;

    @Autowired
    private ErrorService errorService;

    public List<ClientAddressTable> getallAddress(){
        return addressRepo.getallValidAddress();
    }

    public ClientAddressTable getAddress(Long id){
        return addressRepo.getValidAddress(id);
    }

    public String addAddress(@RequestBody ClientAddressTable address){
        address.setValidFlag(1);
        addressRepo.save(address);
        return errorService.getErrorById("ER001");
    }

    public List<ClientAddressTable> globalSearch(String key){
        return addressRepo.globalSearch(key);
    }

    public String update(Long id, ClientAddressTable clientAddress){
        ClientAddressTable ca = addressRepo.getById(id);

        if(clientAddress.getToAddress() != null){
            ca.setToAddress(clientAddress.getToAddress());
        }
        if(clientAddress.getAddressLine1() != null){
            ca.setAddressLine1(clientAddress.getAddressLine1());
        }
        if(clientAddress.getAddressLine2() != null){
            ca.setAddressLine2(clientAddress.getAddressLine2());
        }
        if(clientAddress.getCity() != null){
            ca.setCity(clientAddress.getCity());
        }
        if(clientAddress.getPincode() != null){
            ca.setPincode(clientAddress.getPincode());
        }
        if(clientAddress.getState() != null){
            ca.setState(clientAddress.getState());
        }
        if(clientAddress.getCountry() != null){
            ca.setCountry(clientAddress.getCountry());
        }
        if(clientAddress.getAddressType() != null){
            ca.setAddressType(clientAddress.getAddressType());
        }
        if(clientAddress.getIsPresentAddress() != null){
            ca.setIsPresentAddress(clientAddress.getIsPresentAddress());
        }
        if(clientAddress.getValidFlag() == -1){
            ca.setValidFlag(-1);
        }
        if(clientAddress.getValidFlag() == 1){
            ca.setValidFlag(1);
        }
        addressRepo.save(ca);
        return errorService.getErrorById("ER003");
    }

    public String deleteAddress(Long id){
        ClientAddressTable add = addressRepo.getById(id);
        add.setValidFlag(-1);
        addressRepo.save(add);
        return errorService.getErrorById("ER003");
    }

    public String hardDelete(Long id){
        addressRepo.deleteById(id);
        return errorService.getErrorById("ER003");
    }

}
