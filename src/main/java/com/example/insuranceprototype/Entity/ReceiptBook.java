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
import java.util.List;

@Entity
@Getter
@Setter
public class ReceiptBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long agentId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "agentId", insertable = false, updatable = false)
    private AgentAppointmentDetails agent;

    private String transactionCode;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long receiptNo;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate receiptDate;

    private Long originalReceiptCurrency;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "originalReceiptCurrency", insertable = false, updatable = false)
    private CurrencyCode receiptCurrency;

    private String receiptMethod;

    private Double totalReceiptAmount;

    @OneToMany(mappedBy = "receiptNumber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReceiptReasons> receiptReasons;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    private int validFlag;
}
