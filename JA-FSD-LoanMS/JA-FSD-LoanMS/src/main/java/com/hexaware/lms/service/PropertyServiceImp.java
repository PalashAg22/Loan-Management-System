package com.hexaware.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.lms.dto.PropertyDTO;
import com.hexaware.lms.entities.LoanApplication;
import com.hexaware.lms.entities.Property;
import com.hexaware.lms.repository.LoanApplicationRepository;
import com.hexaware.lms.repository.PropertyRepository;

@Service
public class PropertyServiceImp implements IPropertyService {

	@Autowired
	PropertyRepository propertyRepo;
	@Autowired
	LoanApplicationRepository loanApplicationRepo;;
	

	@Override
	public Property updateProperty(PropertyDTO propertyDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProperty(int propertyId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Property getPropertyById(int popertyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Property createProperty(PropertyDTO propertyDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
