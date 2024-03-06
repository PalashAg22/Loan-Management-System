package com.hexaware.lms.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
//@JsonIgnoreProperties({"Customer","LoanType","Property"})
public class LoanApplication {
	@Id
	@SequenceGenerator(name="loan_sequence",initialValue=2001)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="loan_sequence")// Set the initial value(1001) for LId in the database
	private long loanId;

	private double principal;
	
	private double interestRate;
	
	private int tenureInMonths;
	
	private String status="pending";
	
	private LocalDate loanApplyDate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="loanTypeId")
	private LoanType loanType;
	
	@OneToOne(cascade = CascadeType.ALL , fetch=FetchType.EAGER)
	@JoinColumn(name="property_id")
	private Property property;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private Customer customer;

	public LoanApplication() {
		super();
	}

	public LoanApplication(double principal, int tenureInMonths, LoanType loanType, Property property,
			Customer customer) {
		super();
		this.principal = principal;
		this.tenureInMonths = tenureInMonths;
		this.loanType = loanType;
		this.property = property;
		this.customer = customer;
	}

	public long getLoanId() {
		return loanId;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getTenureInMonths() {
		return tenureInMonths;
	}

	public void setTenureInMonths(int tenureInMonths) {
		this.tenureInMonths = tenureInMonths;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getLoanApplyDate() {
		return loanApplyDate;
	}

	public void setLoanApplyDate(LocalDate loanApplyDate) {
		this.loanApplyDate = loanApplyDate;
	}

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "LoanApplication [loanId=" + loanId + ", principal=" + principal + ", interestRate=" + interestRate
				+ ", tenureInMonths=" + tenureInMonths + ", status=" + status + ", loanApplyDate=" + loanApplyDate
				+ ", loanTypeId=" + loanType.getLoanTypeId() + ", loanTypeName=" + loanType.getLoanTypeName() + 
				", propertyId=" + property.getPropertyId() + ", propertyValue=" + property.getPropertyValue() + 
				", customerId=" + customer.getCustomerId() + ", customerFirstName=" + customer.getCustomerFirstName() +
				", customerId=" + customer.getPanCardNumber() + "]";
	}

	

	

}
