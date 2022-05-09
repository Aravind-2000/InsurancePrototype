package com.example.insuranceprototype.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TrainingCost{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double baseFee;
    private Double trainerFee;
    private Double venueFee;
    private String currency;
    private Long payBeforeDays;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int validFlag;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @UpdateTimestamp
    private LocalDateTime modifiedDate;
}
