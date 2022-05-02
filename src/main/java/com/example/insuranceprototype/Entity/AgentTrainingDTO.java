package com.example.insuranceprototype.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AgentTrainingDTO {


    private Long id;
    private Long trainingId;
    private TrainingDTO training;
    private Long agentId;
    private AgentAppointmentDetails agent;
    private Boolean isApproved;
    private Long approvedBy;
    private AgentAppointmentDetails approvedByAgent;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate approvedDate;

    private Long totalDays;
    private Long daysAttended;
    private Double trainingScore;
    private String trainingStatus;
    private String comments;

}
