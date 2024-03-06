package com.hexaware.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.lms.entities.LoanType;


@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType, Long> {

	@Query("Select lt from LoanType lt where lt.loanTypeName=?1")
	LoanType findLoanTypeByName(String loanTypeName);
	
	@Query("select lt.loanTypeName from LoanType lt where lt.loanTypeName=?1")
	String getLoanTypeName(String loanTypeName);
}
