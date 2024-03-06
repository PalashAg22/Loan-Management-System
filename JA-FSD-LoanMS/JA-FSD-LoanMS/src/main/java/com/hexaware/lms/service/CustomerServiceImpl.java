package com.hexaware.lms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.lms.dto.CustomerDTO;
import com.hexaware.lms.entities.Customer;
import com.hexaware.lms.exception.CustomerCredentialsAlreadyExists;
import com.hexaware.lms.exception.CustomerNotFoundException;
import com.hexaware.lms.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	Logger logg = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository repo;
	
	@Override
	public boolean login(String role, String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer register(CustomerDTO customerDTO) throws CustomerCredentialsAlreadyExists {
		// TODO Auto-generated method stub
		
		String emailDTO = customerDTO.getEmail().toLowerCase();
		String emailRepo = repo.findEmailExists(emailDTO);
		logg.info("searching for email if already exists");
		if(emailRepo == null) {
			logg.warn("this email is already being used");
			throw new CustomerCredentialsAlreadyExists("Customer with credentials already exists");
		}
		logg.info("creating a new admin");

		Customer customer = new Customer();
		customer.setCustomerFirstName(customerDTO.getCustomerFirstName());
		customer.setCustomerLastName(customerDTO.getCustomerLastName());
		customer.setEmail(customerDTO.getEmail());
		customer.setPhoneNumer(customerDTO.getPhoneNumer());
		customer.setPassword(customerDTO.getPassword());
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setAddress(customerDTO.getAddress());
		customer.setCountry(customerDTO.getCountry());
		customer.setState(customerDTO.getState());
		customer.setCreditScore(customerDTO.getCreditScore());
		customer.setPanCardNumber(customerDTO.getPanCardNumber());
		customer.setIdProof(customerDTO.getIdProof());
		
		return repo.save(customer);
	}

	@Override
	public Customer getCustomerById(long customerId) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		logg.info("searching for customer by using customerId");
		Customer customer = repo.findById(customerId).orElse(null);
		if(customer==null) {
			logg.info("customer with customerId "+customerId +" does not exists");
			throw new CustomerNotFoundException("customer you are looking for does not exists");
		}
		logg.info("customer found successfully with customerId "+customerId);
		return customer;
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		logg.info("returning all registered customer");
		return repo.findAll();
	}
	

}
