package com.example.insuranceprototype.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class AgentTrainingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long trainingId;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "trainingId", updatable = false, insertable = false)
    private Training training;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long agentId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "agentId", updatable = false, insertable = false)
    private AgentAppointmentDetails agent;

    private Boolean isApproved;

    private Long approvedBy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "approvedBy", updatable = false, insertable = false)
    private AgentAppointmentDetails approvedByAgent;


    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate approvedDate;

    private Long totalDays;

    private Long daysAttended;

    private Double trainingScore;

    private String trainingStatus;

    private String comments;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int validFlag;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime modifiedDate;


}
