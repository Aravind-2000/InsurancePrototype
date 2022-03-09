package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.ClientMaintainPersonal;
import com.example.insuranceprototype.Repository.ClientPersonalRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientPersonalRepository clientRepo;

    @Autowired
    private ErrorService errorService;

    public List<ClientMaintainPersonal> getallClients(){
        return clientRepo.getallValidClients();
    }

    public ClientMaintainPersonal getClient(Long id ){
        return clientRepo.getClientById(id);
    }

    public String addClient(ClientMaintainPersonal clientMaintainPersonal){
        clientMaintainPersonal.setValidFlag(1);
        clientRepo.save(clientMaintainPersonal);
        return errorService.getErrorById("ER001");
    }

    public List<ClientMaintainPersonal> search(String key){
        return clientRepo.globalSearch(key);
    }

    public String updateClient(Long id , ClientMaintainPersonal clientDetails){

        ClientMaintainPersonal cmp = clientRepo.getById(id);

        if(clientDetails.getSurName() != null){
            cmp.setSurName(clientDetails.getSurName());
        }

        if(clientDetails.getGivenName() != null){
            cmp.setGivenName(clientDetails.getGivenName());
        }
        if(clientDetails.getSalutation() != null){
            cmp.setSalutation(clientDetails.getSalutation());
        }
        if(clientDetails.getGender() != null){
            cmp.setGender(clientDetails.getGender());
        }
        if(clientDetails.getMarritalStatus() != null){
            cmp.setMarritalStatus(clientDetails.getMarritalStatus());
        }
        if(clientDetails.getAddressid() != null){
            cmp.setAddressid(clientDetails.getAddressid());
        }
        if(clientDetails.getAddress() != null){
            cmp.setAddress(clientDetails.getAddress());
        }
        if(clientDetails.getMobileNumber() != null){
            cmp.setMobileNumber(clientDetails.getMobileNumber());
        }
        if(clientDetails.getPostalCode() != null){
            cmp.setPostalCode(clientDetails.getPostalCode());
        }
        if(clientDetails.getCountry() != null){
            cmp.setCountry(clientDetails.getCountry());
        }
        if(clientDetails.getNationality() != null){
            cmp.setNationality(clientDetails.getNationality());
        }
        if(clientDetails.getNameFormat() != null){
            cmp.setNameFormat(clientDetails.getNameFormat());
        }
        if(!clientDetails.getCompanyDoctor()){
            cmp.setCompanyDoctor(true);
        }
        if(clientDetails.getCompanyDoctor()){
            cmp.setCompanyDoctor(false);
        }
        if(clientDetails.getBirthDate() != null){
            cmp.setBirthDate(clientDetails.getBirthDate());
        }
        if(clientDetails.getBirthPlace() != null){
            cmp.setBirthPlace(clientDetails.getBirthPlace());
        }
        if(clientDetails.getLanguage() != null){
            cmp.setLanguage(clientDetails.getLanguage());
        }
        if(clientDetails.getOccupation() != null){
            cmp.setOccupation(clientDetails.getOccupation());
        }
        if(clientDetails.getBankId() != null){
            cmp.setBankId(clientDetails.getBankId());
        }
        if(clientDetails.getBankAccount() != null){
            cmp.setBankAccount(clientDetails.getBankAccount());
        }
        if(clientDetails.getProofList() != null){
            cmp.setProofList(clientDetails.getProofList());
        }
        clientRepo.save(cmp);
        return errorService.getErrorById("ER003");
    }

    public String deleteClient(Long id){
        ClientMaintainPersonal client = clientRepo.getById(id);
        client.setValidFlag(-1);
        clientRepo.save(client);
        return errorService.getErrorById("ER003");
    }

}
