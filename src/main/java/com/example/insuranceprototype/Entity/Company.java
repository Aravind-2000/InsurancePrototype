package com.example.insuranceprototype.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Getter
@Setter
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long companyId;

	private String companyName;

	private String companyCountry;

	private String companyLicenseNumber;

	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate companyLicenseIssueDate;

	private Long companyCurrency;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "companyCurrency", updatable = false, insertable = false)
	private CurrencyCode currency;

	private String companyStatus;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int isValid;

	@CreationTimestamp
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private LocalDateTime companyCreatedDate;

	@UpdateTimestamp
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private LocalDateTime companyModifiedDate;

}
