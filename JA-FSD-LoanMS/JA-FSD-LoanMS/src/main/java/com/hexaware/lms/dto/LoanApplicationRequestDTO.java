package com.hexaware.lms.dto;

import com.hexaware.lms.entities.LoanApplication;
import com.hexaware.lms.entities.Property;

public class LoanApplicationRequestDTO {

	private LoanApplicationDTO loanApplicationDTO;
	
	private PropertyDTO propertyDTO;

	public LoanApplicationDTO getLoanApplicationDTO() {
		return loanApplicationDTO;
	}

	public void setLoanApplicationDTO(LoanApplicationDTO loanApplicationDTO) {
		this.loanApplicationDTO = loanApplicationDTO;
	}

	public PropertyDTO getPropertyDTO() {
		return propertyDTO;
	}

	public void setPropertyDTO(PropertyDTO propertyDTO) {
		this.propertyDTO = propertyDTO;
	}

	
	
}
