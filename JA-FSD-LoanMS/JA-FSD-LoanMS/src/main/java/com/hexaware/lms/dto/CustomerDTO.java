package com.hexaware.lms.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDTO {
	

//	@NotBlank
//	@Pattern(regexp="[A-Z][a-z]{3,10}")
	private String customerFirstName;

//	@NotBlank
//	@Pattern(regexp="[A-Z][a-z]{3,10}")
	private String customerLastName;

//	@NotBlank
//	@Pattern(regexp="^[1-9][0-9]{3-10}")
	private long phoneNumer;

	@Email
	private String email;

	@Size(min=6, max=15)
	private String password;

	
	private LocalDate dateOfBirth;

	@NotBlank
	private String address;

	
	private String country = "India";

	@NotBlank
	private String state;

	@Min(400)
	@Max(1000)
	private int creditScore;

	@Pattern(regexp="[A-Z]{5}[0-9]{4}[A-Z]{1}")
	private String panCardNumber;

	private byte[] idProof;

	public CustomerDTO() {
		super();
	}

	public CustomerDTO(String customerFirstName, String customerLastName, long phoneNumer, String email,
			String password, LocalDate dateOfBirth, String address, String state, int creditScore, String panCardNumber,
			byte[] idProof) {
		super();
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.phoneNumer = phoneNumer;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.state = state;
		this.creditScore = creditScore;
		this.panCardNumber = panCardNumber;
		this.idProof = idProof;
	}

	
	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public long getPhoneNumer() {
		return phoneNumer;
	}

	public void setPhoneNumer(long phoneNumer) {
		this.phoneNumer = phoneNumer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	public String getPanCardNumber() {
		return panCardNumber;
	}

	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}

	public byte[] getIdProof() {
		return idProof;
	}

	public void setIdProof(byte[] idProof) {
		this.idProof = idProof;
	}

}
