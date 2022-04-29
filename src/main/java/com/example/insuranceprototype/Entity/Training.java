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
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainingTopic;
    private String trainingDesc;
    private String trainingType;
    private String trainingMode;
    private String trainingLevel;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate endDate;


    private String trainingTime;
    private String trainer;
    private Double trainingCost;
    private String sponsoredBy;


    @OneToMany(mappedBy = "trainingId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AgentTrainingDetails> listOfAgents = new ArrayList<>();

    private Long continuanceId;

    @OneToMany(mappedBy = "continuanceId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Training> continuanceList = new ArrayList<>();


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int validFlag;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime modifiedDate;

}
