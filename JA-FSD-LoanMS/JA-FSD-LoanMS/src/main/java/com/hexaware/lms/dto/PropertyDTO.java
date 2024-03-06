package com.hexaware.lms.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;

public class PropertyDTO {

	@NotBlank
	private String propertyAddress;

	@NotBlank
	private double propertyAreaInm2;

	@NotBlank
	private double propertyValue;

	@NotBlank
	@Lob
	private byte[] propertyProof;

	public PropertyDTO() {
		super();
	}

	public PropertyDTO(String propertyAddress, double propertyAreaInm2, double propertyValue, byte[] propertyProof) {
		super();
		this.propertyAddress = propertyAddress;
		this.propertyAreaInm2 = propertyAreaInm2;
		this.propertyValue = propertyValue;
		this.propertyProof = propertyProof;
		//this.loanApplicationId = loanApplicationId;
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
}
