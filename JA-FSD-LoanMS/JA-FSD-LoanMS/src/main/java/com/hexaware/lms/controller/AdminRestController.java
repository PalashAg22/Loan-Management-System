package com.hexaware.lms.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.lms.dto.AdminDTO;
import com.hexaware.lms.dto.LoanTypeDTO;
import com.hexaware.lms.entities.Admin;
import com.hexaware.lms.entities.Customer;
import com.hexaware.lms.entities.LoanApplication;
import com.hexaware.lms.entities.LoanType;
import com.hexaware.lms.exception.AdminCredentialsAlreadyExists;
import com.hexaware.lms.exception.CustomerNotFoundException;
import com.hexaware.lms.exception.LoanApplicationNotFoundException;
import com.hexaware.lms.exception.LoanStatusNotFoundException;
import com.hexaware.lms.exception.LoanTypeAlreadyExists;
import com.hexaware.lms.exception.LoanTypeNameNotFoundException;
import com.hexaware.lms.exception.LoanTypeNotFoundException;
import com.hexaware.lms.repository.LoanApplicationRepository;
import com.hexaware.lms.service.IAdminService;
import com.hexaware.lms.service.ICustomerService;
import com.hexaware.lms.service.ILoanApplicationService;
import com.hexaware.lms.service.ILoanTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

	Logger logg = Logger.getLogger(AdminRestController.class);
	
	@Autowired
	IAdminService adminService;
	
	@Autowired
	ILoanApplicationService loanService;
	
	@Autowired
	LoanApplicationRepository loanApplicationRepo;
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	ILoanTypeService loanTypeService;
	
	@PostMapping("/registerAdmin")
	public Admin registerAdmin(@RequestBody @Valid AdminDTO adminDTO) throws AdminCredentialsAlreadyExists {
		logg.info("registering new admin");
		return adminService.register(adminDTO);
	}
	@PostMapping("/create/LoanType")
	public LoanType createLoanType(@RequestBody @Valid LoanTypeDTO loanTypeDTO) throws LoanTypeAlreadyExists {
		logg.info("creating loan-type");
		return loanTypeService.addLoanType(loanTypeDTO);
	}
	@GetMapping("/allLoanType")
	public List<LoanType> findAllLoanType(){
		logg.info("viewing all available LoanType");
		return loanTypeService.getAllLoanType();
	}
	
	@GetMapping("/findByLoanTypeName/{loanTypeName}")
	public LoanType findByLoanTypeName(@PathVariable String loanTypeName) throws LoanTypeNameNotFoundException {
		logg.info("viewing loanType having name = "+loanTypeName);
		return loanTypeService.getLoanTypeByName(loanTypeName);
	}
	
	@PutMapping(value="/updateStatus/{loanId}/{status}")
	public void updateLoanApplicationStatus(@PathVariable long loanId, @PathVariable String status) throws LoanApplicationNotFoundException {
		logg.info("updating LoanApplication status of customer, where new status = "+status);
		loanService.updateloanApplicationStatusOfCustomer(loanId, status);
	}
	
	@GetMapping("/getLoanApplicationById/{loanApplicationId}")
	public LoanApplication findLoanApplicationById(@PathVariable long loanApplicationId) throws LoanApplicationNotFoundException {
		logg.info("viewing applied LoanApplication of customer having loanId = "+loanApplicationId);
		return loanService.getLoanApplicationById(loanApplicationId);
	}
	
	@GetMapping("/customerAppliedLoan/{customerId}")
	public List<LoanApplication> findAppliedLoansOfCustomerById(@PathVariable long customerId) throws CustomerNotFoundException{
		logg.info("viewing all applied LoanApplication of customer having customerId = "+customerId);
		return loanService.getAllLoanApplicationOfCustomerById(customerId);
	}
	
	@GetMapping("/allLoanApplication")
	public List<LoanApplication> findAllLoanApplications(){
		logg.info("viewing all applied LoanApplication of all customer");
		return loanService.getAllAppliedLoan();
	}
	
	
	@GetMapping("/viewCustomer/{customerId}")
	public Customer findCustomerById(@PathVariable @Valid long customerId) throws CustomerNotFoundException {
		logg.info("viewing customer details having customerId = "+customerId);
		return customerService.getCustomerById(customerId);
	}
	
	@GetMapping("/viewAllCustomer")
	public List<Customer> findAllCustomer(){
		logg.info("viewing all customer details");
		return customerService.getAllCustomer();
	}
	
	@GetMapping("/viewAllLoanAppliedByLoantype/{loanType}")
	public List<LoanApplication> findAllLoanAppliedByLoanType(@PathVariable String loanType) throws LoanTypeNotFoundException{
		logg.info("viewing all applied LoanApplication of all customers having loanType = "+loanType);
		return loanService.getAllCustomerAppliedLoansByLoanType(loanType);
	}
	
	@GetMapping("/viewAllLoanAppliedByStatus/{status}")
	public List<LoanApplication> findAllLoanAppliedByStatus(@PathVariable String status) throws LoanStatusNotFoundException{
		logg.info("viewing all appllied LoanApplication of all customers having status = "+status);
		return loanService.getAllCustomerAppliedLoansByStatus(status);
	}
	
	/*
	 * @GetMapping("/viewall") public List<LoanApplication> viewAllAppliedLoans(){
	 * return loanService.viewAllCustomerAppliedLoans(); }
	 * 
	 * @GetMapping("/getAllCustomers") public List<Customer> getAllCustomers(){
	 * return customerService.getAllCustomer(); }
	 * 
	 * @GetMapping("/getCustomerAllLoans") public List<LoanApplication>
	 * getCustomerAllLoansById(@RequestBody long customerId){
	 * 
	 * return loanService.viewAllLoansOfCustomerById(customerId); }
	 */
}
