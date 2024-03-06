package com.hexaware.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.lms.entities.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

	@Query("select p.propertyAddress from Property p where p.propertyAddress=?1")
	String findPropertyNameExists(String propertyAddressDTO);
	
}
