package com.example.insuranceprototype.Service;

import com.example.insuranceprototype.Entity.Training;
import com.example.insuranceprototype.Entity.TrainingDTO;
import com.example.insuranceprototype.Repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingDTOService {


    public TrainingDTO mapEntityToDto(Training training) {
        TrainingDTO result = new TrainingDTO();
        result.setId(training.getId());
        result.setTrainingTopic(training.getTrainingTopic());
        result.setTrainingDesc(training.getTrainingDesc());
        result.setTrainingType(training.getTrainingType());
        result.setTrainingLevel(training.getTrainingLevel());
        result.setTrainingMode(training.getTrainingMode());
        result.setStartDate(training.getStartDate());
        result.setEndDate(training.getEndDate());
        result.setTrainingTime(training.getTrainingTime());
        result.setTrainer(training.getTrainer());
        result.setTrainingCost(training.getTrainingCost());
        result.setSponsoredBy(training.getSponsoredBy());
        return result;
    }


}
