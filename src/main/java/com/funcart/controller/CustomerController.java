
package com.funcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.funcart.Dao.ItemsDao;
import com.funcart.Dao.LoginDao;
import com.funcart.Dao.SignUpDao;
import com.funcart.domain.Customer;
import com.funcart.domain.Item;

@RestController
public class CustomerController {
	
	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private SignUpDao signupDao;
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private ItemsDao itemsDao;
	
	@RequestMapping(value="/loginPage",method=RequestMethod.GET)
	public ModelAndView getLoginPage(){
		ModelAndView mv = new ModelAndView("loginPage");
		return mv;
	}
	
	@RequestMapping(value="/signupPage",method=RequestMethod.GET)
	public ModelAndView getSignupPage(){
		ModelAndView mv = new ModelAndView("signupPage");
		return mv;
	}

	@SuppressWarnings({ "static-access", "rawtypes" })
	@RequestMapping(value = "/login",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity checkLoginDetail(@RequestBody Customer customer){
		Customer ct = null;
		if((ct = loginDao.checkLoginDetail(customer)) != null)
			return new ResponseEntity<Customer>(ct,httpStatus.ACCEPTED);
		else
			return new ResponseEntity<String>("Not Authorized",httpStatus.UNAUTHORIZED);
	}
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value = "/signup",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveSignupDetail(@RequestBody Customer customer) throws Exception{
		Customer customerObj =  null;
		
		try{
			if(signupDao.saveCustomer(customer)){
				httpStatus = httpStatus.CREATED;
				customerObj = customer;
			}else{
				httpStatus = httpStatus.EXPECTATION_FAILED;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
			customerObj = new Customer();
		}
		
		return new ResponseEntity<Customer>(customerObj,httpStatus);
	}
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value="/items",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getItems(){
		List<Item> itemsObj = null;
		if((itemsObj = itemsDao.getItemsList()).isEmpty())
			httpStatus = httpStatus.NOT_FOUND;
		else
			httpStatus = httpStatus.OK;
		
		return new ResponseEntity<List<Item>>(itemsObj,httpStatus);
	}
}