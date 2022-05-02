package com.example.insuranceprototype.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TrainingDTO {

    private Long id;
    private String trainingTopic;
    private String trainingDesc;
    private String trainingType;
    private String trainingMode;
    private String trainingLevel;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate endDate;


    private String trainingTime;
    private String trainer;
    private Double trainingCost;
    private String sponsoredBy;
}
