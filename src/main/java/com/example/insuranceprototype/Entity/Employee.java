package com.example.insuranceprototype.Entity;


import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String employeeId;

    private String employeeName;

    private String employeeEmail;

    private String employeeDesignation;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "employee")
    private List<PersonalDetails> assignedCandidates;

    @Timestamp
    private LocalDateTime createdTime;

    @Timestamp
    private LocalDateTime modifiedTime;
}
