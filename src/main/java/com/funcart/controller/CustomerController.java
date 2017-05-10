
package com.funcart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.service.ItemService;
import com.funcart.dao.service.LoginService;
import com.funcart.dao.service.SignupService;
import com.funcart.domain.dto.CustomerDto;
import com.funcart.domain.dto.ErrorResponse;
import com.funcart.domain.dto.ItemDto;
import com.funcart.domain.dto.LoginDto;
import com.funcart.domain.dto.SignupDto;
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
		
		if(loginDto == null || (StringUtils.isEmpty(loginDto.getName()) || StringUtils.isEmpty(loginDto.getPassword()))
				 || !Validator.passwordValidate(loginDto.getPassword())){
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(400, "Invalid username or password"), httpStatus);
		}
		else{
			try{
				if(loginService.checkLogin(loginDto)){
					httpStatus = httpStatus.OK;
				}
				else{
					httpStatus = httpStatus.UNAUTHORIZED;
					return new ResponseEntity<ErrorResponse>(new ErrorResponse(400, "Invalid username or password"), httpStatus);
				}
			}catch(Exception e){
				httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
				return new ResponseEntity<ErrorResponse>(new ErrorResponse(500, "Internal server error"), httpStatus);
			}
		}
		return new ResponseEntity<CustomerDto>(loginService.getCustomerDto(),httpStatus);
	}
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value = "/signup",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveSignupDetail(@RequestBody SignupDto signupDto) throws Exception{
		if(signupDto == null || StringUtils.isEmpty(signupDto.getEmail()) || StringUtils.isEmpty(signupDto.getPassword()) 
				|| StringUtils.isEmpty(signupDto.getUsername()) || signupDto.getPhoneNumber() == 0L)
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(400, "Empty field"), httpStatus);
		
		if(signupService.checkSignupDetail(signupDto))
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(400, signupService.getStr()), httpStatus);

		try{
			if(signupService.saveCustomer(signupDto)){
				httpStatus = httpStatus.CREATED;
			}else{
				httpStatus = httpStatus.EXPECTATION_FAILED;
				return new ResponseEntity<ErrorResponse>(new ErrorResponse(417,signupService.getStr()), httpStatus);
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(500, "Internal server error"), httpStatus);
		}
		
		return new ResponseEntity<SignupDto>(signupService.getSignupDto(),httpStatus);
	}
}