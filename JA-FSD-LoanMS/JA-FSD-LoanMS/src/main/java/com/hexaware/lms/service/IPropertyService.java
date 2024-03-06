package com.hexaware.lms.service;

import com.hexaware.lms.dto.PropertyDTO;
import com.hexaware.lms.entities.Property;

public interface IPropertyService {

	Property createProperty(PropertyDTO propertyDTO);
	
	Property updateProperty(PropertyDTO propertyDTO);
	
	void deleteProperty(int propertyId);
	
	Property getPropertyById(int popertyId);
	
}
