/**
 * 
 */
package com.hexaware.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.lms.entities.Customer;
import com.hexaware.lms.entities.LoanApplication;

import jakarta.transaction.Transactional;

/**
 * 
 */
@Transactional
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
	
//	@Query("select la from LoanApplication la where la.customer=?1")
//	List<LoanApplication> customer(Customer customer);
	
	@Modifying
	@Query("update LoanApplication la set la.status=?1 where la.loanId=?2")
	void updateStatus(String status, long loanId);
	
	@Query("select la  from LoanApplication la where la.customer=?1")
	List<LoanApplication> findCustomerAppliedLoan(Customer customer);

	@Query("select la from LoanApplication la where la.status=?1")
	List<LoanApplication> findLoanAppliedBasedOnStatus(String status);
	
	@Query("select la from LoanApplication la where la.loanType.loanTypeName = ?1")
	List<LoanApplication> findLoanAppliedBasedOnLoanType(String loanType);
	
	@Query("select la.loanId from LoanApplication la where la.customer.customerId=?1 and la.status=?2")
	long findByCustomerIdAndStatus(long customerId,String status); // for customer use
	
	@Query("select la from LoanApplication la where la.customer.customerId=?1 and la.loanId=?2")
	LoanApplication findCustomerLoanApplicationById(long customerId,long loanId);
	
}
