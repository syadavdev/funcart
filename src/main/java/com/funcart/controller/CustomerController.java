
package com.funcart.controller;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.service.LoginService;
import com.funcart.dao.service.SignupService;
import com.funcart.domain.dto.Response.ErrorResponse;
import com.funcart.domain.dto.customer.CustomerDto;
import com.funcart.domain.dto.customer.LoginDto;
import com.funcart.domain.dto.customer.SignupDto;
import com.funcart.validator.Validator;

@RestController
public class CustomerController {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SignupService signupService; 
	
/*	@RequestMapping(value="/loginPage",method=RequestMethod.GET)
	public ModelAndView getLoginPage(){
		ModelAndView mv = new ModelAndView("loginPage");
		return mv;
	}
	
	@RequestMapping(value="/signupPage",method=RequestMethod.GET)
	public ModelAndView getSignupPage(){
		ModelAndView mv = new ModelAndView("signupPage");
		return mv;
	}*/

	@SuppressWarnings({ "static-access", "rawtypes" })
	@RequestMapping(value = "/login",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity checkLoginDetail(@RequestBody LoginDto loginDto) throws Exception{
		String errorMsg = "Invalid username or password";
		if(loginDto == null || (StringUtils.isEmpty(loginDto.getEmailOrPhoneNumber()) || StringUtils.isEmpty(loginDto.getPassword()))
				 || !Validator.passwordValidate(loginDto.getPassword())){
			httpStatus = httpStatus.BAD_REQUEST;
		}
		else{
			try{
				if(loginService.checkLogin(loginDto)){
					httpStatus = httpStatus.OK;
					return new ResponseEntity<CustomerDto>(loginService.getCustomerDto(),httpStatus);
				}
				else{
					httpStatus = httpStatus.UNAUTHORIZED;
					errorMsg = loginService.getErrorMsg();
				}
			}catch(NoResultException e){
				httpStatus = httpStatus.NOT_FOUND;
				errorMsg = "Not_Found";
			}catch(Exception e){
				httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
				errorMsg = "Internal Server error";
				e.printStackTrace();
			}
		}
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(), errorMsg),httpStatus);
	}
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value = "/signup",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveSignupDetail(@RequestBody SignupDto signupDto) throws Exception{
		String errorMsg = "Empty Field";
		if(signupDto == null || StringUtils.isEmpty(signupDto.getEmail()) || StringUtils.isEmpty(signupDto.getPassword()) 
				|| StringUtils.isEmpty(signupDto.getName()) || signupDto.getPhoneNumber() == 0L){
			httpStatus = httpStatus.BAD_REQUEST;
		}else if(!signupService.checkSignupDetail(signupDto)){
			httpStatus = httpStatus.BAD_REQUEST;
			errorMsg = signupService.getErrorStr();
		}else{
			try{
				if(signupService.saveCustomer(signupDto)){
					httpStatus = httpStatus.CREATED;
					return new ResponseEntity<SignupDto>(signupService.getSignupDto(),httpStatus);
				}else{
					httpStatus = httpStatus.EXPECTATION_FAILED;
					errorMsg = signupService.getErrorStr();
				}
			}catch(Exception e){
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorMsg = "Internal server error";
			}
		}
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(), errorMsg), httpStatus);
	}
}