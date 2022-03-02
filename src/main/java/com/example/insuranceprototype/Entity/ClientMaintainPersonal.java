package com.example.insuranceprototype.Entity;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ClientMaintainPersonal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String surName;
    private String givenName;
    private String salutation;
    private String gender;
    private String marritalStatus;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long addressid;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "addressid", updatable = false, insertable = false)
    private ClientAddressTable address;

    private String mobileNumber;
    private String postalCode;
    private String country;
    private String nationality;
    private String nameFormat;
    private Boolean companyDoctor;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private String birthPlace;
    private String language;
    private String category;
    private String documentNumber;
    private String occupation;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long bankId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bankId", updatable = false, insertable = false)
    private BankAccount bankAccount;


    private int validFlag;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;
}
