package com.funcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.engine.spi.ValueInclusion;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import com.funcart.domain.Customer;
import com.funcart.domain.dto.SignupDto;
import com.funcart.validator.Validator;

@Repository
public class SignupDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(rollbackOn=Exception.class)
	public boolean saveCustomer(SignupDto signupDto)throws Exception{
		
		
		boolean flag = false;
		
		try{
			
			if(checkSignupDetail(signupDto)){
			
				int result = em.createNativeQuery("INSERT INTO customer (username, password, email, phoneNumber,shippingAddress,billingAddress) "
								+ "VALUES (?, ?, ?, ?, ?, ?)")
								.setParameter(1,signupDto.getUsername())
								.setParameter(2, signupDto.getPassword())
								.setParameter(3, signupDto.getEmail())
								.setParameter(4, signupDto.getPhoneNumber())
								.setParameter(5, "")
								.setParameter(6, "")
								.executeUpdate();
				
					if(result > 0)
						flag = true;
				}
			}catch(Exception e){
				throw e;
			}
		return flag;
	}
	
	public boolean checkSignupDetail(SignupDto signupDto){
		boolean flag = false;
		
		boolean name = 
				Validator.nameValidate(signupDto.getUsername()) && !findEmpty(signupDto.getUsername()) ? true : false;
		boolean email = 
				Validator.emailValidate(signupDto.getEmail()) && !findEmpty(signupDto.getEmail()) ? true : false;
		boolean password = 
				Validator.passwordValidate(signupDto.getPassword()) && !findEmpty(signupDto.getPassword()) ? true : false;
		boolean phoneNumber = 
				Validator.phoneNumberValidate(Long.toString(signupDto.getPhoneNumber())) ? true : false;
		
		if(!name && !email && !password && !phoneNumber){
				flag = false;
		}
		else{
			flag = true;
		}
		return flag;
	}
	
	public boolean findEmpty(String str){
		if(StringUtils.isEmpty(str)){
			return true;
		}
		else{
			return false;
		}
	}
}