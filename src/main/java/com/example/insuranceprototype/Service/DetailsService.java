package com.example.insuranceprototype.Service;

import com.example.insuranceprototype.Entity.PersonalDetails;
import com.example.insuranceprototype.Entity.Status;
import com.example.insuranceprototype.Repository.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailsService {

    @Autowired
    private DetailsRepository detailsRepo;

    public List<PersonalDetails> getAllDetails(){
        return detailsRepo.findAll();
    }

    public String saveDetails(PersonalDetails details){
        details.setCurrentStatus("Captured");
        detailsRepo.save(details);
        return "Candidate ID " + details.getId() + " details saved successfully";
    }

    public Optional<PersonalDetails> getByCandidateId(Long id){
        return detailsRepo.findById(id);
    }

    public String deleteDetails(Long id ){
        detailsRepo.deleteById(id);
        return "Candidate ID " + id + " details deleted successfully";
    }

    public String updateDetails(Long id , PersonalDetails details){

        PersonalDetails pd = detailsRepo.getById(id);

        if(details.getName() != null){
            pd.setName(details.getName());
        }
        if(details.getEmail() != null){
            pd.setEmail(details.getEmail());
        }
        if(details.getMobileNumber() != null){
            pd.setMobileNumber(details.getMobileNumber());
        }
        if(details.getDateOfBirth() != null){
            pd.setDateOfBirth(details.getDateOfBirth());
        }
        if(details.getHighestQualification() != null){
            pd.setHighestQualification(details.getHighestQualification());
        }
        if(details.getProof() != null){
            pd.setProof(details.getProof());
        }
        if(details.getProofId() != null){
            pd.setProofId(details.getProofId());
        }
        if(details.getCommunication() != null){
            pd.setCommunication(details.getCommunication());
        }
        if(details.getAvailableDateAndTime() != null){
            pd.setAvailableDateAndTime((details.getAvailableDateAndTime()));
        }
        if(details.getEmployee() != null){
            pd.setEmployee(details.getEmployee());
            pd.setCurrentStatus("Assigned");
        }
        detailsRepo.save(pd);
        return "Candidate ID " + id + " updated successfully";
    }
}
