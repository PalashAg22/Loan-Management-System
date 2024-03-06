package com.hexaware.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.lms.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
	
	@Query("select c.email from Customer c where c.email=?1")
	String findEmailExists(String email);
	
}
