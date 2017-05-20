package com.funcart.dao.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.UpdateCustomerDao;
import com.funcart.domain.dto.customer.ChangeEmailDto;
import com.funcart.domain.dto.customer.ChangePasswordDto;

@Service
public class ChangeEmailPasswordService {
	
	@Autowired
	private UpdateCustomerDao updateCustomerDao;
	
	private String errorMsg;

	public boolean changePassword(ChangePasswordDto changePasswordDto)throws Exception{
		boolean flag = false;
		if(updateCustomerDao.changePassword(changePasswordDto.getEmail(),changePasswordDto.getNewPassword(),
				changePasswordDto.getOldPassword())){
			flag = true;
		}else{
			errorMsg = "Error In Updating Details";
			flag = false;
		}
		
		return flag;
	}
	
	public boolean changeEmail(ChangeEmailDto changeEmailDto)throws Exception{
		boolean flag = false;
		if(updateCustomerDao.changeEmail(changeEmailDto.getNewEmail(), changeEmailDto.getOldEmail(), 
				changeEmailDto.getPassword()))
			flag = true;
		else{
			errorMsg = "Error In Updating Details";
			flag = false;
		}
	
		return flag;
	}
	
	public boolean checkExistingCustomer(String email,String password) throws Exception{
		boolean flag = false;
		try{
			if(updateCustomerDao.checkCustomer(email,password)){
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
	
	public boolean checkExistingEmail(String email){
		boolean flag = false;
		try{
			if(updateCustomerDao.checkCustomerEmail(email)){
				flag = true;
				errorMsg = "Phone Number Already Exist";
			}else{
				flag = false;
			}
		}catch(NoResultException  e){
			flag = false;
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
