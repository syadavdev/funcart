package com.funcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.funcart.domain.Customer;
import com.funcart.domain.dto.SignupDto;
import com.funcart.validator.Validator;

@Repository
public class SignupDao {
	
	@PersistenceContext
	private EntityManager em;
	
	SignupDto signupDtoObj;
	
	@Transactional(rollbackOn=Exception.class)
	public boolean saveCustomer(SignupDto signupDto)throws Exception{
		
		boolean flag = false;
		int result = 0; 

		try{
			
			result = em.createNativeQuery("INSERT INTO customer (username, password, email, phoneNumber,shippingAddress,billingAddress) "
							+ "VALUES (?, ?, ?, ?, ?, ?)")
						.setParameter(1,signupDto.getUsername())
						.setParameter(2, signupDto.getPassword())
						.setParameter(3, signupDto.getEmail())
						.setParameter(4, signupDto.getPhoneNumber())
						.setParameter(5, "")
						.setParameter(6, "")
						.executeUpdate();

		}catch(Exception e){
			signupDto.setPhoneNumber(0);
			signupDto.setEmail(null);
			signupDto.setPassword(null);
			signupDto.setUsername(null);
			throw e;
		}
		
		if(result > 0){
			flag = true;
		}else{
			flag = false;
			signupDtoObj.setPhoneNumber(0);
			signupDtoObj.setEmail(null);
			signupDtoObj.setPassword(null);
			signupDtoObj.setUsername(null); 
		}
		
		return flag;
	}
	
	public boolean checkSignupDetail(SignupDto signupDto){
		boolean check = false;
		boolean[] flag = {false,false,false,false};
		signupDtoObj = new SignupDto();
		signupDtoObj.setPassword(signupDto.getPassword());
		signupDtoObj.setPhoneNumber(signupDto.getPhoneNumber());
		signupDtoObj.setEmail(signupDto.getEmail());
		signupDtoObj.setUsername(signupDto.getUsername());
		
		flag[0] = 
				Validator.nameValidate(signupDto.getUsername()) && !findEmpty(signupDto.getUsername()) ? true : false;
		flag[1] = 
				Validator.emailValidate(signupDto.getEmail()) && !findEmpty(signupDto.getEmail()) ? true : false;
		flag[2] = 
				Validator.passwordValidate(signupDto.getPassword()) && !findEmpty(signupDto.getPassword()) ? true : false;
		flag[3] = 
				Validator.phoneNumberValidate(Long.toString(signupDto.getPhoneNumber())) ? true : false;
		
		if(!flag[0])
			signupDtoObj.setUsername("Not Valid");
		if(!flag[1])
			signupDtoObj.setEmail("Not Valid");
		if(!flag[2])
			signupDtoObj.setPassword("Not Valid");
		if(!flag[3])
			signupDtoObj.setPhoneNumber(9000000009L);
		
		for(boolean b : flag){
			if(b){
				check = true;
			}else{
				check = false;
				break;
			}
		}
			
		return check;
	}
	
	public boolean findEmpty(String str){
		if(StringUtils.isEmpty(str)){
			return true;
		}
		else{
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean alreadyExist(SignupDto signupDto)throws Exception{
		
		List<Customer> customers = null;
		boolean var1 = false,var2 = false,var3 = false,flag = false;
		
		String already = "Already Exist";
		signupDtoObj = new SignupDto();
		signupDtoObj.setPassword(signupDto.getPassword());
		signupDtoObj.setPhoneNumber(signupDto.getPhoneNumber());
		signupDtoObj.setEmail(signupDto.getEmail());
		signupDtoObj.setUsername(signupDto.getUsername());
		
		try{
			customers =  em.createQuery("from Customer as o where o.username = ? or o.phoneNumber = ? or o.email = ?")
			     		   .setParameter(0,signupDto.getUsername())
						   .setParameter(1, signupDto.getPhoneNumber())
						   .setParameter(2, signupDto.getEmail())
						   .getResultList();
		}catch(Exception e){
			signupDto.setPhoneNumber(0);
			signupDto.setEmail(null);
			signupDto.setPassword(null);
			signupDto.setUsername(null);
			throw e;
		}
		
		for(Customer customer :  customers){
			if(var1 = customer.getUsername().equals(signupDto.getUsername()))
				signupDtoObj.setUsername(already);
			if(var2 = customer.getEmail().equals(signupDto.getEmail()))
				signupDtoObj.setEmail(already);
			
			long l1 = customer.getPhoneNumber();
			long l2 = signupDto.getPhoneNumber();
			if(var3 = (l1 == l2))
				signupDtoObj.setPhoneNumber(9000000009L);	
		}
		
		if(var1 || var2 || var3){
			flag = true;
			signupDtoObj.setPassword("********");
		}
		
		return flag;
	}

	//getter setter
	public SignupDto getSignupDtoObj() {
		return signupDtoObj;
	}

	public void setSignupDtoObj(SignupDto signupDtoObj) {
		this.signupDtoObj = signupDtoObj;
	}
}