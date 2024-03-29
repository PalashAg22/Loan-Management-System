package com.hexaware.lms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AdminDTO {

//	@NotBlank
//	@Pattern(regexp="[A-Z][a-z]{3,10}")
	private String adminFirstName;

//	@NotBlank
//	@Pattern(regexp="[A-Z][a-z]{3,10}")
	private String adminLastName;

	@Email
	private String email;

	
	@Size(min=6, max=15)
	private String password;

	public AdminDTO() {
		super();
	}

	public AdminDTO(String adminFirstName, String adminLastName, String email, String password) {
		super();
		this.adminFirstName = adminFirstName;
		this.adminLastName = adminLastName;
		this.email = email;
		this.password = password;
	}

	public String getAdminFirstName() {
		return adminFirstName;
	}

	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}

	public String getAdminLastName() {
		return adminLastName;
	}

	public void setAdminLastName(String adminLastName) {
		this.adminLastName = adminLastName;
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
}
