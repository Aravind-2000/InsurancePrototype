package com.example.insuranceprototype.Service;

import com.example.insuranceprototype.Entity.OfficeStructure;
import com.example.insuranceprototype.Repository.OfficeStructureRepository;
import com.example.insuranceprototype.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OfficeStructureService {

    @Autowired
    private OfficeStructureRepository officeStructureRepository;

    @Autowired
    private ErrorService errorService;

    public List<OfficeStructure> getAll(){
        return officeStructureRepository.findAll();
    }
    public Optional<OfficeStructure> getById(Long id){
        return officeStructureRepository.findById(id);
    }

    public List<OfficeStructure> getByOfficeLevel(Long id){
        return officeStructureRepository.getByOfficeLevel(id);
    }

    public String addOffice(OfficeStructure officeStructure){
        officeStructureRepository.save(officeStructure);
        return "Added";
    }

    public List<OfficeStructure> getUpLevel(Long id){
        return officeStructureRepository.getUpLevel(id);
    }

    public String updateOffice(Long id, OfficeStructure officeStructure){
        OfficeStructure office = officeStructureRepository.getById(id);
        if(officeStructure.getCompanyId() != null){
            office.setCompanyId(officeStructure.getCompanyId());
        }
        if(officeStructure.getCompany() != null){
            office.setCompany(officeStructure.getCompany());
        }
        if(officeStructure.getOfficeLevelId() != null){
            office.setOfficeLevelId(officeStructure.getOfficeLevelId());
        }
        if(officeStructure.getUpLevelOfficeId() != null){
            office.setUpLevelOfficeId(officeStructure.getUpLevelOfficeId());
        }
        if(officeStructure.getCountryCode() != null){
            office.setCountryCode(officeStructure.getCountryCode());
        }
        officeStructureRepository.save(office);
        return errorService.getErrorById("ER003");
    }

    public String deactivateCompany(Long id){
        OfficeStructure office = officeStructureRepository.getById(id);
        office.setOfficeStatus("inactive");
        return errorService.getErrorById("ER003");
    }
}
