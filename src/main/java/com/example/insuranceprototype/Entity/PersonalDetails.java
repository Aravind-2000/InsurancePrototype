package com.example.insuranceprototype.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class PersonalDetails  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String mobileNumber;
    private String email;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate dateOfBirth;
    private String highestQualification;

    private String proof;

    private String proofId;

    private String communication;

    @JsonFormat(pattern = "MM-dd-yyyy HH:mm")
    private LocalDateTime availableDateAndTime;

    private Long employee;

    private String currentStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private Quantitative quants;
}
