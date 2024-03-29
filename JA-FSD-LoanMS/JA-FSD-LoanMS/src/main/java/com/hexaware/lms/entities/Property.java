package com.hexaware.lms.entities;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="property_details")
public class Property {
	@Id
	@SequenceGenerator(name="property_sequence",initialValue=3001)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="property_sequence")
	private int propertyId;
	
	private String propertyAddress;

	private double propertyAreaInm2;
	
	private double propertyValue;
	
	@Lob
	private byte[] propertyProof;
	
	//@OneToOne( mappedBy="property",fetch=FetchType.LAZY)
	//private LoanApplication loanApplication;
	
	public Property() {
		super();
	}

	public Property(String propertyAddress, double propertyAreaInm2, double propertyValue, byte[] propertyProof
			) {
		super();
		this.propertyAddress = propertyAddress;
		this.propertyAreaInm2 = propertyAreaInm2;
		this.propertyValue = propertyValue;
		this.propertyProof = propertyProof;
		//this.loanApplication = loanApplication;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public double getPropertyAreaInm2() {
		return propertyAreaInm2;
	}

	public void setPropertyAreaInm2(double propertyAreaInm2) {
		this.propertyAreaInm2 = propertyAreaInm2;
	}

	public double getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(double propertyValue) {
		this.propertyValue = propertyValue;
	}

	public byte[] getPropertyProof() {
		return propertyProof;
	}

	public void setPropertyProof(byte[] propertyProof) {
		this.propertyProof = propertyProof;
	}

	/*
	 * public LoanApplication getLoanApplication() { return loanApplication; }
	 * 
	 * public void setLoanApplication(LoanApplication loanApplication) {
	 * this.loanApplication = loanApplication; }
	 */

	@Override
	public String toString() {
		return "Property [propertyId=" + propertyId + ", propertyAddress=" + propertyAddress + ", propertyAreaInm2="
				+ propertyAreaInm2 + ", propertyValue=" + propertyValue + ", propertyProof="
				+ Arrays.toString(propertyProof) + "]";
	}
	
	
}
