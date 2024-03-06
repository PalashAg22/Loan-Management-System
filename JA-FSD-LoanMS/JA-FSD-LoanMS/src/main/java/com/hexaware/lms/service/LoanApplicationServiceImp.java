package com.hexaware.lms.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.lms.controller.CustomerRestController;
import com.hexaware.lms.dto.LoanApplicationDTO;
import com.hexaware.lms.dto.PropertyDTO;
import com.hexaware.lms.entities.Customer;
import com.hexaware.lms.entities.LoanApplication;
import com.hexaware.lms.entities.LoanType;
import com.hexaware.lms.entities.Property;
import com.hexaware.lms.exception.CustomerNotFoundException;
import com.hexaware.lms.exception.LoanApplicationNotFoundException;
import com.hexaware.lms.exception.LoanStatusNotFoundException;
import com.hexaware.lms.exception.LoanTypeNotFoundException;
import com.hexaware.lms.exception.PropertyCredentialsAlreadyExists;
import com.hexaware.lms.repository.CustomerRepository;
import com.hexaware.lms.repository.LoanApplicationRepository;
import com.hexaware.lms.repository.LoanTypeRepository;
import com.hexaware.lms.repository.PropertyRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanApplicationServiceImp implements ILoanApplicationService {

	Logger logg = Logger.getLogger(LoanApplicationServiceImp.class);
	
	@Autowired
	CustomerRepository customerRepo;
	@Autowired 
	LoanTypeRepository loanTypeRepo;
	@Autowired
	PropertyRepository propertyRepo;
	@Autowired
	LoanApplicationRepository loanApplicationRepo;
	
	@Autowired
	ICustomerService customerService;
	
	@Override
	public LoanApplication applyLoan(LoanApplicationDTO loanApplicationDTO, PropertyDTO propertyDTO) throws PropertyCredentialsAlreadyExists {
		logg.info("applying for new loan");
		long customerId = loanApplicationDTO.getCustomerId();
		long loanTypeId = loanApplicationDTO.getLoanTypeId();
		
		Customer customer = customerRepo.findById(customerId).orElse(null);
		LoanType loanType = loanTypeRepo.findById(loanTypeId).orElse(null);
		double intrestRate = loanType.getLoanInterestBaseRate();
		logg.info("searching for property address if already exists");
		String propertyAddressDTO = propertyDTO.getPropertyAddress();
		String propertyAddressRepo = propertyRepo.findPropertyNameExists(propertyAddressDTO);
		
		if(propertyAddressRepo == null) {
			logg.warn("property address is already being used");
			throw new PropertyCredentialsAlreadyExists("Property credentials already exists");
		}
		
		Property property = new Property();
		property.setPropertyAddress(propertyDTO.getPropertyAddress());
		property.setPropertyAreaInm2(propertyDTO.getPropertyAreaInm2());
		property.setPropertyProof(propertyDTO.getPropertyProof());
		property.setPropertyValue(propertyDTO.getPropertyValue());
		
		logg.info("successfully applied for new loan");
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setCustomer(customer);
		loanApplication.setProperty(property);
		loanApplication.setLoanType(loanType);
		loanApplication.setInterestRate(intrestRate);
		loanApplication.setPrincipal(loanApplicationDTO.getPrincipal());
		loanApplication.setTenureInMonths(loanApplicationDTO.getTenureInMonths());
		loanApplication.setLoanApplyDate(loanApplicationDTO.getLoanApplyDate());
		
//		
//		property.setLoanApplication(loanApplication);
//		customer.addLoanApplication(loanApplication);
//		loanType.addAppliedLoan(loanApplication);
//		
		customerRepo.save(customer);
		loanTypeRepo.save(loanType);
		propertyRepo.save(property);
		return loanApplicationRepo.save(loanApplication);
	}
	
	@Override
	public LoanApplication getLoanApplicationByLoanId(long customerId, long loanId) throws LoanApplicationNotFoundException  {
		logg.info("searching for loan application if exists");
		LoanApplication loanApplication = loanApplicationRepo.findCustomerLoanApplicationById(customerId, loanId);
		if(loanApplication==null) {
			throw new LoanApplicationNotFoundException("loan application having loanId "+ loanId+" does not exists");
		}
		return loanApplication;
	}

	
	
	@Override
	public List<LoanApplication> getAllAppliedLoan() {
		logg.info("viewing all applied LoanApplication of all customer");
		return loanApplicationRepo.findAll();
	}
	
	@Override
	public void updateloanApplicationStatusOfCustomer(long loanId, String status) throws LoanApplicationNotFoundException {
		logg.info("searching for loan application having id "+loanId);
		LoanApplication loanApplication = getLoanApplicationById(loanId);
		if(loanApplication == null) {
			logg.warn("loan application having id "+loanId+" does not exists");
			throw new LoanApplicationNotFoundException("Loan Application you are looking for does not exists");
		}
		logg.info("updating LoanApplication status of customer, where new status = "+status);
		loanApplicationRepo.updateStatus(status, loanId);
	}
	@Override
	public List<LoanApplication> getloanAppliedByCustomerId(long customerId) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		logg.info("searching loanApplication of customer having id = "+customerId);
		Customer customer = customerRepo.findById(customerId).orElse(null);
		if(customer == null) {
			logg.warn("customer having id "+customerId+" does not exists");
			throw new CustomerNotFoundException("customer you are looking for not exists");
		}
		return loanApplicationRepo.findCustomerAppliedLoan(customer);
	}
	
	@Override
	public List<LoanApplication> getAllLoanApplicationOfCustomerById(long customerId) throws CustomerNotFoundException {

		Customer customer = customerService.getCustomerById(customerId);
		logg.info("searching all LoanApplication by customer having customerId = "+customerId);
		return loanApplicationRepo.findCustomerAppliedLoan(customer);
	}
	@Override
	public LoanApplication getLoanApplicationById(long loanId) throws LoanApplicationNotFoundException {

		logg.info("searching applied LoanApplication of customer having loanId = "+loanId);
		LoanApplication loanApplication = loanApplicationRepo.findById(loanId).orElse(null);
		if(loanApplication == null) {
			logg.warn("loan application does not exists having loanId "+loanId);
			throw new LoanApplicationNotFoundException("Loan Application you are looking for does not exists");
		}
		logg.info("returning loan application of customer");
		return loanApplication;
	}
	@Override
	public List<LoanApplication> getAllCustomerAppliedLoansByStatus(String status) throws LoanStatusNotFoundException {
		logg.info("searching all appllied LoanApplication of all customers having status = "+status);
		List<LoanApplication> list = loanApplicationRepo.findLoanAppliedBasedOnStatus(status);
		if(list.isEmpty()) {
			logg.warn("wrong status entered or status type is empty");
			throw new LoanStatusNotFoundException("wrong status entered or status type is empty");
		}
		return list;
	}
	@Override
	public List<LoanApplication> getAllCustomerAppliedLoansByLoanType(String loanTypeName) throws LoanTypeNotFoundException {
		logg.info("searching all applied LoanApplication of all customers having loanType = "+loanTypeName);
		LoanType isLoanTypeAvailable = loanTypeRepo.findLoanTypeByName(loanTypeName);
		if( isLoanTypeAvailable == null) {
			logg.warn("loantype name does not exists");
			throw new LoanTypeNotFoundException("LoanType you are looking for does not exists");
		}
		logg.info("returning all loan application of loanType name "+loanTypeName);
		return loanApplicationRepo.findLoanAppliedBasedOnLoanType(loanTypeName);
	}
	
}
