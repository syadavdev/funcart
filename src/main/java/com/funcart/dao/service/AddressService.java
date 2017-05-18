package com.funcart.dao.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.AddressDao;
import com.funcart.domain.dto.AddressDto;

@Service
public class AddressService {
	
	@Autowired
	private AddressDao addressDao;

	private String errorMsg;
	
	public boolean saveCustomer(AddressDto addressDto) throws Exception{
		if(addressDao.saveCustomer(addressDto))
			return true;
		else{
			errorMsg = "Error In Saving Details";
			return false;
		}
	}
	
	public boolean checkExistingCustomer(String email) throws Exception {
		boolean flag = false;
		try{
			if(addressDao.checkCustomer(email)){
				flag = true;
			}
			else{
				flag = false;
				errorMsg = "Error In Finding Customer";
			}
		}catch(NoResultException e){
			flag = false;
			errorMsg = "Customer Not_Found";
		}
		
		return flag;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
