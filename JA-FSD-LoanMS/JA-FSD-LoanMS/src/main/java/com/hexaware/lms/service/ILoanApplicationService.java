package com.hexaware.lms.service;

import java.util.List;

import com.hexaware.lms.dto.LoanApplicationDTO;
import com.hexaware.lms.dto.PropertyDTO;
import com.hexaware.lms.entities.LoanApplication;
import com.hexaware.lms.exception.CustomerNotFoundException;
import com.hexaware.lms.exception.LoanApplicationNotFoundException;
import com.hexaware.lms.exception.LoanStatusNotFoundException;
import com.hexaware.lms.exception.LoanTypeNotFoundException;
import com.hexaware.lms.exception.PropertyCredentialsAlreadyExists;

public interface ILoanApplicationService {

	// services a customer can perform
	LoanApplication applyLoan(LoanApplicationDTO loanApplicationDTO, PropertyDTO propertyDTO) throws PropertyCredentialsAlreadyExists;
	
	List<LoanApplication> getloanAppliedByCustomerId(long customerId)throws CustomerNotFoundException;
	
	LoanApplication getLoanApplicationByLoanId(long customerId, long loanId) throws LoanApplicationNotFoundException;
	
//pending  List<LoanApplication> getCustomerLoanAppliedStatus(long customerId, String status);
	
	
	// services a amdin can perform 
	
	List<LoanApplication> getAllAppliedLoan();
	
	List<LoanApplication> getAllLoanApplicationOfCustomerById(long customerId) throws CustomerNotFoundException;
	
	void updateloanApplicationStatusOfCustomer(long loanId, String status) throws LoanApplicationNotFoundException;

	LoanApplication getLoanApplicationById(long loanId) throws LoanApplicationNotFoundException;
	
	List<LoanApplication> getAllCustomerAppliedLoansByStatus(String status) throws LoanStatusNotFoundException;
	
	List<LoanApplication> getAllCustomerAppliedLoansByLoanType(String loanType) throws LoanTypeNotFoundException;

	//LoanApplication updateAppliedLoan (LoanApplicationDTO loanApplicationDTO,long loanId);
	
//	List<LoanApplication> viewAppliedLoanByLoanType(String loanTypeName);
//
//	List<LoanApplication> viewAppliedLoanByStatus (String status);
	

	// common services b/w admin and customer
	
	//	LoanApplication viewLoanApplicationByLoanId(long loanId);
		
		//void deleteLoanApplicationById(long loanId);
	
//	List<LoanApplication> viewLoansOfCustomerByFirstName(String customerFirstName);
	
//	List<LoanApplication> viewLoansOfCustomerByLastName(String customerLastName);
	
//LoanApplication viewLoanApplicationByLoanId(long loanId);
	
//	
//	
//	
}
