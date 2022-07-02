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
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class PolicyHeader {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId", insertable = false,updatable = false)
    private Company company;

    private Long agentId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "agentId", insertable = false,updatable = false)
    private AgentAppointmentDetails agent;


    private String policyNumber;

    private String premium;
    private String cStatus;
    private String  cpStatus;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate billDate;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate paidDate;

    private Long currency;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "currency", insertable = false, updatable = false)
    private CurrencyCode policyCurrency;

    private String product;


    @OneToMany(mappedBy = "policyNumber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<PolicyCover> policyCovers = new ArrayList<>();

    private int isActive;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime modifiedDate;





}
