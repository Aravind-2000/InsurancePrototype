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
public class AgentAppointmentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long clientId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "clientId", insertable = false, updatable = false)
    private ClientMaintainPersonal client;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateAppointed;

    private String exclusive;
    private String previousAgent;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate prevDateOfTermination;

    private String statusFlag;
    private String distributionChannel;
    private String branch;
    private String areaCode;
    private String agentType;
    private String reportingTo;

    private String payMethod;
    private String payFrequency;
    private String currencyType;
    private String minimumAmount;

    private String bonusAllocation;

    private String basicCommission;
    private String renewalCommission;
    private String servicingCommission;
    private String commissionClass;

    private int validFlag;


    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;
}
