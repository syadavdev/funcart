
package com.funcart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.service.CustomerService;
import com.funcart.dao.service.ItemService;
import com.funcart.domain.dto.CustomerDto;
import com.funcart.domain.dto.ItemListDto;
import com.funcart.domain.dto.LoginDto;
import com.funcart.domain.dto.SignupDto;

@RestController
public class CustomerController {
	
	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ItemService itemService;
	
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

	@SuppressWarnings({ "static-access", "rawtypes", "null" })
	@RequestMapping(value = "/login",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity checkLoginDetail(@RequestBody LoginDto loginDto) throws Exception{
		CustomerDto customerDto = null;
		try{
			if((customerDto = customerService.checkLogin(loginDto)) != null){
				httpStatus = httpStatus.ACCEPTED;
			}
			else{
				httpStatus = httpStatus.UNAUTHORIZED;
				customerDto.setUsername("Unauthorized");
			}
		}catch(Exception e){
			httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
			customerDto = new CustomerDto();
			customerDto.setUsername("Exception Catches");
		}
		
		return new ResponseEntity<CustomerDto>(customerDto,httpStatus);
	}
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value = "/signup",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveSignupDetail(@RequestBody SignupDto signupDto) throws Exception{
		SignupDto signupDtoObj = new SignupDto();
		signupDtoObj.setUsername("Not Saved");
		try{
			signupDtoObj = customerService.saveCustomer(signupDto);
			if(customerService.matching(signupDtoObj, signupDto)){
				httpStatus = httpStatus.CREATED;
			}else{
				httpStatus = httpStatus.EXPECTATION_FAILED;
				signupDtoObj.setUsername("Exception Failed");
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			signupDtoObj.setUsername("Exception Catches");
		}
		
		return new ResponseEntity<SignupDto>(signupDtoObj,httpStatus);
	}
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value="/items",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getItems(){
		List<ItemListDto> itemListDto = new ArrayList<ItemListDto>();
		try{
			if((itemListDto = itemService.getList()).isEmpty())
				httpStatus = httpStatus.NOT_FOUND;
			else
				httpStatus = httpStatus.OK;
		}catch(Exception e){
			httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<ItemListDto>>(itemListDto,httpStatus);
	}
}