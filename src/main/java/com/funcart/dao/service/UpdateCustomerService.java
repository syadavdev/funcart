package com.funcart.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.funcart.dao.UpdateCustomerDao;
import com.funcart.domain.dto.customer.UpdateCustomerDto;
import com.funcart.validator.Validator;

@Service
public class UpdateCustomerService {
	
	@Autowired
	private UpdateCustomerDao updateCustomerDao;
	
	private String query;
	private String errorMsg;
	
	public boolean updateCustomer(UpdateCustomerDto updateCustomerDto) throws Exception{
		boolean flag = false;
		if(updateCustomerDao.updateCustomer(getQuery()))
			flag = true;
		else{
			errorMsg = "Error In Updating Details";
			flag = false;
		}
		return flag;
	}
	
	public boolean validateNewDetails(UpdateCustomerDto updateCustomerDto){
		boolean flag = true;
		errorMsg = "";
		
		if(!Validator.nameValidate(updateCustomerDto.getNewName()) && !StringUtils.isEmpty(updateCustomerDto.getNewName())){
			errorMsg += "Name Invalid,";
		}
		if(!Validator.passwordValidate(updateCustomerDto.getPassword()) && !StringUtils.isEmpty(updateCustomerDto.getPassword())){
			errorMsg += "Password Invalid,";
		}
		if(!Validator.phoneNumberValidate(Long.toString(updateCustomerDto.getNewPhoneNumber())) && updateCustomerDto.getNewPhoneNumber() != 0L){
			errorMsg += "PhoneNumber Invalid,";
		}
		if(!Validator.emailValidate(updateCustomerDto.getEmail()) && !StringUtils.isEmpty(updateCustomerDto.getEmail())){
			errorMsg += "Email Invalid";
		}
		
		if(errorMsg.isEmpty()){
			flag = true;
		}else{
			flag = false;
		}
		
		return flag;
	}
	
	public boolean checkExistingCustomer(UpdateCustomerDto updateCustomerDto) throws Exception {
		boolean flag = false;
		try{
			if(updateCustomerDao.checkCustomer(updateCustomerDto.getEmail(),updateCustomerDto.getPassword())){
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
	
	public boolean checkPhoneNumber(Long phoneNumber){
		boolean flag = false;
		try{
			if(updateCustomerDao.checkCustomerNumber(phoneNumber)){
				flag = true;
				errorMsg = "Phone Number Already Exist";
			}else{
				flag = false;
			}
		}catch(NoResultException e){
			flag = false;
		}
		return flag;
	}
	
	public boolean makingQuery(UpdateCustomerDto updateCustomerDto){
		boolean flag = false;
		List<String> fields = new ArrayList<String>();
		query = "UPDATE Customer SET";
		
		if(!StringUtils.isEmpty(updateCustomerDto.getNewName())){
			fields.add(" username = '"+updateCustomerDto.getNewName()+"'");
			flag = true;
		}
		if(updateCustomerDto.getNewPhoneNumber() != 0L){
			fields.add(" phoneNumber = '"+Long.toString(updateCustomerDto.getNewPhoneNumber())+"'");
			flag = true;
		}
		if(!StringUtils.isEmpty(updateCustomerDto.getNewShippingAddress())){
			fields.add(" shippingAddress = '"+updateCustomerDto.getNewShippingAddress()+"'");
			flag = true;
		}
		if(!StringUtils.isEmpty(updateCustomerDto.getNewBillingAddress())){
			fields.add(" billingAddress = '"+updateCustomerDto.getNewBillingAddress()+"'");
			flag = true;
		}
		
		int i = 0;
		for(String str : fields){
			if(!str.isEmpty()){
				if(i > 0){
					query += ",";
				}
				query += str;
				i++;
			}
		}
		query += " where email = '"+updateCustomerDto.getEmail()+"' And password = '"+updateCustomerDto.getPassword()+"'";
		return flag;
	}
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
