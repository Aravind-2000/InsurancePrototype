package com.example.insuranceprototype.Service;


import com.example.insuranceprototype.Entity.PersonalDetails;
import com.example.insuranceprototype.Entity.Quantitative;
import com.example.insuranceprototype.Repository.DetailsRepository;
import com.example.insuranceprototype.Repository.QuantitativeRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuantitativeService {

    @Autowired
    private QuantitativeRepositroy quantitativeRepo;

    @Autowired
    private DetailsRepository detailsRepository;


    public List<Quantitative> getAllQuantitative(){
        return quantitativeRepo.findAll();
    }

    public Optional<Quantitative> getOneQuantitative(Long id){
        return quantitativeRepo.findById(id);
    }

    public String saveDetails(Quantitative quantitative) {
        quantitativeRepo.save(quantitative);
        PersonalDetails pd = detailsRepository.getById(quantitative.getId());
        if(quantitative.getResult().equals("Passed") || quantitative.getResult().equals("Failed")){
            pd.setCurrentStatus("Interview Completed");
        }
        detailsRepository.save(pd);
        return " New Quantative Is Saved Successfully";
    }

    public Quantitative updateDetails(Long id, Quantitative quantitative){

        Quantitative qat = quantitativeRepo.getById(id);


        if(quantitative.getNumericalAbility() != 0L) {
            qat.setNumericalAbility(quantitative.getNumericalAbility());
        }

        if(quantitative.getNumericalAbilityComments() != null){
            qat.setNumericalAbilityComments(quantitative.getNumericalAbilityComments());
        }

        if(quantitative.getLogicalReasoning() != 0L) {
            qat.setLogicalReasoning(quantitative.getLogicalReasoning());
        }

        if(quantitative.getLogicalReasoningComments() != null){
            qat.setLogicalReasoningComments(quantitative.getLogicalReasoningComments());
        }

        if(quantitative.getVerbalAbility() != 0L) {
            qat.setVerbalAbility(quantitative.getVerbalAbility());
        }

        if(quantitative.getVerbalAbilityComments() != null){
            qat.setVerbalAbilityComments(quantitative.getVerbalAbilityComments());
        }

        if(quantitative.getCodingAndDecoding() != 0L) {
            qat.setCodingAndDecoding(quantitative.getCodingAndDecoding());
        }

        if(quantitative.getCodingAndDecodingComments() != null){
            qat.setCodingAndDecodingComments(quantitative.getCodingAndDecodingComments());
        }

        if(quantitative.getOverallRating() != 0L){
            qat.setOverallRating(quantitative.getOverallRating());
        }

        if(quantitative.getResult() != null){
            qat.setResult(quantitative.getResult());
        }
        return quantitativeRepo.save(qat);
    }
}
