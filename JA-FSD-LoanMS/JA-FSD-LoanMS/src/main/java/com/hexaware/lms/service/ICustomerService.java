package com.hexaware.lms.service;

import java.util.List;

import com.hexaware.lms.dto.CustomerDTO;
import com.hexaware.lms.entities.Customer;
import com.hexaware.lms.entities.LoanApplication;
import com.hexaware.lms.exception.CustomerCredentialsAlreadyExists;
import com.hexaware.lms.exception.CustomerNotFoundException;

public interface ICustomerService {
	boolean login(String role, String username, String password);

	Customer register(CustomerDTO customerDTO) throws CustomerCredentialsAlreadyExists;
	
	Customer getCustomerById(long customerId)throws CustomerNotFoundException;

	List<Customer> getAllCustomer();

	
	
}
