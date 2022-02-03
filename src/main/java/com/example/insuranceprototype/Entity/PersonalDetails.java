package com.example.insuranceprototype.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    private String highestQualification;

    @Enumerated(EnumType.STRING)
    private Proof proof;

    private String proofId;

    @Enumerated(EnumType.STRING)
    private ModeOfCommunication communication;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime availableDateAndTime;

}
