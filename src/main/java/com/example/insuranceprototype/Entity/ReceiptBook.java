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

//    private String transactionCode;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String receiptNo;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate receiptDate;

    private Long originalReceiptCurrencyId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "originalReceiptCurrencyId", insertable = false, updatable = false)
    private CurrencyCode receiptCurrency;

    private String receiptMethod;

    private Double totalReceiptAmount;

    @OneToMany(mappedBy = "receiptId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionJournal> receiptReasons;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int validFlag;
}
