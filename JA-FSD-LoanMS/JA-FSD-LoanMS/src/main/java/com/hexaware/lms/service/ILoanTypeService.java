package com.hexaware.lms.service;

import java.util.List;

import com.hexaware.lms.dto.LoanTypeDTO;
import com.hexaware.lms.entities.LoanType;
import com.hexaware.lms.exception.LoanTypeAlreadyExists;
import com.hexaware.lms.exception.LoanTypeNameNotFoundException;
import com.hexaware.lms.exception.LoanTypeNotFoundException;

public interface ILoanTypeService {

	LoanType addLoanType(LoanTypeDTO loanTypeDTO) throws LoanTypeAlreadyExists;
		
	void deleteLoanTypeById (long  loanTypeId);
	
	List<LoanType> getAllLoanType();
	
	LoanType getLoanTypeById(long loanTypeId) throws LoanTypeNotFoundException;
	
	LoanType getLoanTypeByName(String loanTypeName) throws LoanTypeNameNotFoundException;
}
