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
public class ReceiptReasons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String receiptReason;

    private Long receiptNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    private int validFlag;

}
