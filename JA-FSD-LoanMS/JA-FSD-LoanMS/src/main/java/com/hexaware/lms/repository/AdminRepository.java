package com.hexaware.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.lms.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{
	
	@Query("select a.email from Admin a where a.email=?1")
	String findEmailExists(String emailDTO);
	
}
