package com.example.insuranceprototype.Entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingModuleDTO {

    private Long id;
    private String trainingTopic;
    private String trainingDesc;
    private Long noOfDays;
    private String trainingLevel;
}
