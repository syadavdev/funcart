package com.funcart.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.SignupDao;
import com.funcart.domain.dto.customer.SignupDto;
import com.funcart.validator.Validator;

@Service
public class SignupService {
	
	@Autowired
	private SignupDao signupDao;
	
	private SignupDto signupDto;
	private String errorStr;

	public boolean saveCustomer(SignupDto signupDto) throws Exception{
		boolean flag = false;
		errorStr = "";
		
		if(!signupDao.alreadyExist(signupDto)){
			if(signupDao.saveCustomer(signupDto)){
				this.signupDto = signupDto;
				flag = true;
			}else{
				errorStr = "Saving Signup Detail Failed";
			}
		}else{
			errorStr = "Customer Already Exist";
		}
		return flag;
	}
	public boolean checkSignupDetail(SignupDto signupDto){
		
		boolean check = false;
		boolean[] flag = {false,false,false,false};
		String str = "";
		
		flag[0] = 
				Validator.nameValidate(signupDto.getName()) ? true : false;
		flag[1] = 
				Validator.emailValidate(signupDto.getEmail()) ? true : false;
		flag[2] = 
				Validator.passwordValidate(signupDto.getPassword()) ? true : false;
		flag[3] = 
				Validator.phoneNumberValidate(Long.toString(signupDto.getPhoneNumber())) ? true : false;
		
		if(!flag[0]){
			str = str + "Username-Invalid ";
		}
		if(!flag[1]){
			str = str + "Email-Invalid ";
		}
		if(!flag[2]){
			str = str + "Password-Invalid ";
		}
		if(!flag[3]){
			str = str + "PhoneNumber-Invalid ";
		}
		
		if(str.isEmpty())
			check = true;
		else
			setErrorStr(str);
		
		return check;
	}

	public String getErrorStr() {
		return errorStr;
	}
	public void setErrorStr(String str) {
		this.errorStr = str;
	}
	public SignupDto getSignupDto() {
		return signupDto;
	}

	public void setSignupDto(SignupDto signupDto) {
		this.signupDto = signupDto;
	}
}