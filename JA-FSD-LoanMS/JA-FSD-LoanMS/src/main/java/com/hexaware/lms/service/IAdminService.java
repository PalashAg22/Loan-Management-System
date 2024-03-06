package com.hexaware.lms.service;

import java.util.List;

import com.hexaware.lms.dto.AdminDTO;
import com.hexaware.lms.entities.Admin;
import com.hexaware.lms.entities.Customer;
import com.hexaware.lms.entities.LoanApplication;
import com.hexaware.lms.entities.LoanType;
import com.hexaware.lms.exception.AdminCredentialsAlreadyExists;

public interface IAdminService {
	boolean login(String role, String username, String password);

	Admin register(AdminDTO adminDTO) throws AdminCredentialsAlreadyExists;
	
}
