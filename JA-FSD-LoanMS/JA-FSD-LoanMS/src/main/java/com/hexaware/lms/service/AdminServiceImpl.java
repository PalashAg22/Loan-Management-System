package com.hexaware.lms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.lms.dto.AdminDTO;
import com.hexaware.lms.entities.Admin;
import com.hexaware.lms.exception.AdminCredentialsAlreadyExists;
import com.hexaware.lms.repository.AdminRepository;

@Service
public class AdminServiceImpl implements IAdminService {

	Logger logg = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminRepository repo;
	
	@Override
	public boolean login(String role, String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Admin register(AdminDTO admin) throws AdminCredentialsAlreadyExists {
		// TODO Auto-generated method stub
		String emailDTO = admin.getEmail().toLowerCase();
		String emailRepo = repo.findEmailExists(emailDTO);
		logg.info("searching for email if already exists");
		if(emailRepo == null) {
			logg.warn("this email is already being used");
			throw new AdminCredentialsAlreadyExists("Admin with credentials already exists");
		}
		logg.info("creating a new admin");
		Admin admin1 = new Admin();
		admin1.setAdminFirstName(admin.getAdminFirstName());
		admin1.setAdminLastName(admin.getAdminLastName());
		admin1.setEmail(admin.getEmail());
		admin1.setPassword(admin.getPassword());
		
		return repo.save(admin1);
	}

}
