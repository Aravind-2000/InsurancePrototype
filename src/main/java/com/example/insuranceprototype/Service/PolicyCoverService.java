package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.PolicyCover;
import com.example.insuranceprototype.Repository.PolicyCoverRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyCoverService {


    @Autowired
    private PolicyCoverRepository repository;

    @Autowired
    private ErrorService errorService;



    public List<PolicyCover> getAllPolicyCover(){
        return repository.getAllValid();
    }

    public PolicyCover getPolicyCover(Long id){
        return repository.findById(id).get();
    }

    public String addPolicyCover(PolicyCover policyCover){
        policyCover.setIsActive(1);
        repository.save(policyCover);
        return errorService.getErrorById("ER001");
    }

    public String updatePolicyCover(Long id, PolicyCover newOne){

        PolicyCover oldOne = repository.getById(id);

        if(newOne.getPolicyNumber() != null){
            oldOne.setPolicyNumber(newOne.getPolicyNumber());
        }

        if(newOne.getLife() != null){
            oldOne.setLife(newOne.getLife());
        }

        if(newOne.getCoverage() != null){
            oldOne.setCoverage(newOne.getCoverage());
        }

        if(newOne.getCoverageName() != null){
            oldOne.setCoverageName(newOne.getCoverageName());
        }

        if(newOne.getInstantPremium() != null){
            oldOne.setInstantPremium(newOne.getInstantPremium());
        }

        if(newOne.getSumAssured() != null){
            oldOne.setSumAssured(newOne.getSumAssured());
        }
        if(newOne.getRiskEndDate() != null){
            oldOne.setRiskEndDate(newOne.getRiskEndDate());
        }

        if(newOne.getRider() != null){
            oldOne.setRider(newOne.getRider());
        }

        if(newOne.getPremiumEndDate() != null){
            oldOne.setPremiumEndDate(newOne.getPremiumEndDate());
        }

        if(newOne.getCStatus() != null){
            oldOne.setCStatus(newOne.getCStatus());
        }

        if(newOne.getPStatus() != null){
            oldOne.setPStatus(newOne.getPStatus());
        }

        if(newOne.getCompanyId() != null){
            oldOne.setCompanyId(newOne.getCompanyId());
        }

        repository.save(oldOne);
        return errorService.getErrorById("ER003");
    }

    public String softDeletePolicyCover(Long id){
        PolicyCover oldOne = repository.getById(id);
        oldOne.setIsActive(-1);
        repository.save(oldOne);
        return errorService.getErrorById("ER003");

    }

    public String hardDeletePolicyCover(Long id){
        repository.deleteById(id);
        return errorService.getErrorById("ER003");
    }

    public List<PolicyCover> search(String key){
        return repository.globalSearch(key);
    }

}
