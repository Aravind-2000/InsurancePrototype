package com.example.insuranceprototype.Entity;


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
public class PolicyCover {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long policyHeaderId;

    private Long policyNumber;

    private Long life;
    private Long coverage;
    private Long rider;
    private Long coverageNameId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "coverageNameId", insertable = false, updatable = false)
    private ProductCoverageNames coverageName;


    private String instantPremium;
    private String sumAssured;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate riskEndDate;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate premiumEndDate;


    private Long CoverageStatusId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CoverageStatusId", insertable = false, updatable = false)
    private Cpstatus CoverageStatus;


    private Long PolicyStatusId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PolicyStatusId", insertable = false, updatable = false)
    private Cpstatus PolicyStatus;

    private Long companyId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId", insertable = false,updatable = false)
    private Company company;


    private int isActive;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime modifiedDate;


}
