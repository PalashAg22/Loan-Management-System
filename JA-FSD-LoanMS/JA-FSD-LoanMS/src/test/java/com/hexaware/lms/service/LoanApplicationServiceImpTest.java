package com.hexaware.lms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.lms.dto.LoanApplicationDTO;
import com.hexaware.lms.dto.PropertyDTO;
import com.hexaware.lms.entities.LoanApplication;
import com.hexaware.lms.exception.CustomerNotFoundException;
import com.hexaware.lms.exception.LoanApplicationNotFoundException;
import com.hexaware.lms.exception.PropertyCredentialsAlreadyExists;

@SpringBootTest
class LoanApplicationServiceImpTest {

	Logger logg = Logger.getLogger(LoanApplicationServiceImpTest.class);
	
	@Autowired
	ILoanApplicationService serviceTest;
	
	@Test
	void testApplyLoan() throws PropertyCredentialsAlreadyExists {
		LocalDate date = LocalDate.now();
		LoanApplicationDTO loanApplicationDTO = new LoanApplicationDTO(100000,40,date,4,1001);
		PropertyDTO propertyDTO = new PropertyDTO();
		
		logg.info("applying for loan in test class..");
		LoanApplication loanApplication = serviceTest.applyLoan(loanApplicationDTO, propertyDTO);
		
		assertNotNull(loanApplication);
	}

	@Test
	void testGetAllAppliedLoan() {
		List<LoanApplication> list = serviceTest.getAllAppliedLoan();
		logg.info("getting list of all applied loans by customers");
		assertTrue(list.size()>0);
	}

	@Test
	void testUpdateloanApplicationStatusOfCustomer() throws LoanApplicationNotFoundException {
		int loanId = 2002;
		String status = "approved";
		logg.warn("updating loan application status of customer..");
		serviceTest.updateloanApplicationStatusOfCustomer(loanId, status);
		
		LoanApplication updatedLoanApplication = serviceTest.getLoanApplicationById(loanId);
		logg.info("fetching the updated loan application status of customer");
		assertEquals(status , updatedLoanApplication.getStatus());
		
	}

	@Test
	void testGetloanAppliedByCustomerId() throws CustomerNotFoundException {
		long customerId = 1001;
		List<LoanApplication> list = serviceTest.getloanAppliedByCustomerId(customerId);
		assertFalse(list.size()<0);
		
		long customerId2 = 100001;
		List<LoanApplication> list2 = serviceTest.getloanAppliedByCustomerId(customerId2);
		assertFalse(list2.size()>0);
		
	}

	@Test
	void testGetAllLoanApplicationOfCustomerById() throws CustomerNotFoundException {
		long customerId = 1001;
		List<LoanApplication> list = serviceTest.getloanAppliedByCustomerId(customerId);
		assertTrue(list.size() > 0);
		
		long customerId2 = 1000001;
		List<LoanApplication> list2 = serviceTest.getloanAppliedByCustomerId(customerId2);
		assertFalse(list2.size()>0);
	}

	@Test
	void testGetLoanApplicationById() throws LoanApplicationNotFoundException {
		long loanId = 2001;
		LoanApplication getLoan = serviceTest.getLoanApplicationById(loanId);
		assertNotNull(getLoan);
		
		long loanId2 = 1000001;
		LoanApplication notFound = serviceTest.getLoanApplicationById(loanId2);
		assertNull(notFound);
	}

}
