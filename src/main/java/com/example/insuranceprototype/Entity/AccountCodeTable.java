package com.example.insuranceprototype.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class AccountCodeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountCode;

    private String accountShortDescription;

    private String accountLongDescription;


//    private Long companyCurrencyId;
//
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "companyCurrencyId", updatable = false, insertable = false)
//    private CurrencyCode companyCurrency;


    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime updatedDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int validFlag;



}
