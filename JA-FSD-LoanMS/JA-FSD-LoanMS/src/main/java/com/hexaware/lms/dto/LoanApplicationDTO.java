package com.hexaware.lms.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class LoanApplicationDTO {
	@Min(10000)
	@Max(10000000)
	private double principal;
	
	@Min(12)
	@Max(120)
	private int tenureInMonths;
	
	
	private LocalDate loanApplyDate;
	
	@NotBlank
	private long loanTypeId;
	
	@NotBlank
	private long customerId;

	public LoanApplicationDTO() {
		super();
	}

	public LoanApplicationDTO(double principal, int tenureInMonths, LocalDate loanApplyDate, long loanTypeId,
			long customerId) {
		super();
		this.principal = principal;
		this.tenureInMonths = tenureInMonths;
		this.loanApplyDate = loanApplyDate;
		this.loanTypeId = loanTypeId;
		this.customerId = customerId;
		
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public int getTenureInMonths() {
		return tenureInMonths;
	}

	public void setTenureInMonths(int tenureInMonths) {
		this.tenureInMonths = tenureInMonths;
	}

	public LocalDate getLoanApplyDate() {
		return loanApplyDate;
	}

	public void setLoanApplyDate(LocalDate loanApplyDate) {
		this.loanApplyDate = loanApplyDate;
	}

	public long getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(long loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
}
