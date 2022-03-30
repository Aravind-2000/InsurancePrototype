package com.example.insuranceprototype.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class OfficeStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long officeId;

    private String officeName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long companyId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId",updatable = false,insertable = false)
    private Company company;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long officeLevelId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "officeLevelId",updatable = false,insertable = false)
    private OfficeLevelParam officeLevelParam;

    private String officeStatus;

    private Long upLevelOfficeId;

    @OneToMany(mappedBy = "upLevelOfficeId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OfficeStructure> downLevelOffice;

    @OneToMany(mappedBy = "officeId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AgentAppointmentDetails> agents;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime modifiedDate;

}