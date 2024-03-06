package com.hexaware.lms.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.lms.dto.CustomerDTO;
import com.hexaware.lms.dto.LoanApplicationRequestDTO;
import com.hexaware.lms.entities.Customer;
import com.hexaware.lms.entities.LoanApplication;
import com.hexaware.lms.entities.LoanType;
import com.hexaware.lms.exception.CustomerCredentialsAlreadyExists;
import com.hexaware.lms.exception.CustomerNotFoundException;
import com.hexaware.lms.exception.LoanApplicationNotFoundException;
import com.hexaware.lms.exception.LoanTypeNameNotFoundException;
import com.hexaware.lms.exception.PropertyCredentialsAlreadyExists;
import com.hexaware.lms.service.ICustomerService;
import com.hexaware.lms.service.ILoanApplicationService;
import com.hexaware.lms.service.ILoanTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

	Logger logg = Logger.getLogger(CustomerRestController.class);

	@Autowired
	ICustomerService customerService;
	
	@Autowired
	ILoanTypeService loanTypeService;
	
	@Autowired
	ILoanApplicationService loanService;
	
	@PostMapping("/register")
	public Customer createCustomer(@RequestBody @Valid CustomerDTO customerDTO) throws CustomerCredentialsAlreadyExists {
		return customerService.register(customerDTO);
	}
	
	@PostMapping("/applyloan")
	public LoanApplication applyForLoan(@RequestBody @Valid LoanApplicationRequestDTO loanApplicationRequestDTO) throws PropertyCredentialsAlreadyExists {
	
		return loanService.applyLoan(loanApplicationRequestDTO.getLoanApplicationDTO(),loanApplicationRequestDTO.getPropertyDTO());
	}

	@GetMapping("/findByLoanTypeName/{loanTypeName}")
	public LoanType findByLoanTypeName(@PathVariable String loanTypeName) throws LoanTypeNameNotFoundException {
		logg.info("viewing LoanType having name = "+loanTypeName);
		return loanTypeService.getLoanTypeByName(loanTypeName);
	}
	@GetMapping("/allLoanType")
	public List<LoanType> findLoanType(){
		logg.info("viewing all available LoanType");
		return loanTypeService.getAllLoanType();
	}
	
	@GetMapping("/findLoanApplicationById/{customerId}/{loanId}")
	public LoanApplication findLoanApplicationById(@PathVariable long customerId, @PathVariable long loanId) throws LoanApplicationNotFoundException {
		return loanService.getLoanApplicationByLoanId(customerId, loanId);
	}
	
	@GetMapping("/appliedLoan/{customerId}")
	public List<LoanApplication> findCustomerAppliedLoans(@PathVariable @Valid long customerId) throws CustomerNotFoundException{
		logg.info("viewing applied loanApplication of customer having id = "+customerId);
		return loanService.getloanAppliedByCustomerId(customerId);
	}
	
	
}
