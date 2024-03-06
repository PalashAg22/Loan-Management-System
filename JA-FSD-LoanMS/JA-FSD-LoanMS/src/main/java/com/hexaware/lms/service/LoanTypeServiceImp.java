package com.hexaware.lms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.lms.dto.LoanTypeDTO;
import com.hexaware.lms.entities.LoanType;
import com.hexaware.lms.exception.LoanTypeAlreadyExists;
import com.hexaware.lms.exception.LoanTypeNameNotFoundException;
import com.hexaware.lms.exception.LoanTypeNotFoundException;
import com.hexaware.lms.repository.LoanTypeRepository;

@Service
public class LoanTypeServiceImp implements ILoanTypeService {

	Logger logg = LoggerFactory.getLogger(LoanTypeServiceImp.class);
	@Autowired
	LoanTypeRepository repo;
	
	@Override
	public LoanType addLoanType(LoanTypeDTO loanTypeDTO) throws LoanTypeAlreadyExists {
		// TODO Auto-generated method stub
		
		logg.info("searching for LoanType if already exixts");
		String loanTypeNameDTO = loanTypeDTO.getLoanTypeName().strip().toUpperCase();
		LoanType loanTypeNameRepo = repo.findLoanTypeByName(loanTypeNameDTO);
		
		if(loanTypeNameRepo != null) {
			logg.warn("this LoanType already exists");
			throw new LoanTypeAlreadyExists("LoanType already exists");
		}
		
		logg.info("creating new LoanType");
		LoanType loanType = new LoanType();
		loanType.setLoanTypeName(loanTypeDTO.getLoanTypeName());
		loanType.setLoanInterestBaseRate(loanTypeDTO.getLoanInterestBaseRate());
		loanType.setLoanManagementFees(loanTypeDTO.getLoanManagementFees());
		return repo.save(loanType);
	}

	@Override
	public void deleteLoanTypeById(long loanTypeId) {
		// TODO Auto-generated method stub
		repo.deleteById(loanTypeId);
	}

	@Override
	public List<LoanType> getAllLoanType() {
		// TODO Auto-generated method stub
		logg.info("returning all available LoanType");
		return repo.findAll();
	}

	@Override
	public LoanType getLoanTypeById(long loanTypeId) throws LoanTypeNotFoundException {
		// TODO Auto-generated method stub
		logg.info("searching for LoanType having Id "+loanTypeId);
		LoanType loanType = repo.findById(loanTypeId).orElse(null);
		if(loanType == null) {
			logg.warn("LoanType by id "+ loanTypeId + " you are looking for does not exists");
			throw new LoanTypeNotFoundException("LoanType by id "+ loanTypeId + " you are looking for does not exists");
		}
		return loanType;
	}

	@Override
	public LoanType getLoanTypeByName(String loanTypeName) throws LoanTypeNameNotFoundException {
		// TODO Auto-generated method stub
		logg.info("searching loanType having name = "+loanTypeName);
		String isLoanTypeNameAvailable = repo.getLoanTypeName(loanTypeName);
		if(isLoanTypeNameAvailable == null) {
			logg.warn("loanType having name = "+loanTypeName+" does not exists");
			throw new LoanTypeNameNotFoundException("loanType having name = "+loanTypeName+" does not exists");
		}
		return repo.findLoanTypeByName(loanTypeName);
	}
	
	
}
