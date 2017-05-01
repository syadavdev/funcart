package com.funcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.funcart.Dao.ItemsDao;
import com.funcart.Dao.LoginDao;
import com.funcart.Dao.SignUpDao;
import com.funcart.domain.Customer;
import com.funcart.domain.Items;

@RestController
public class CustomerController {
	
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

	@RequestMapping(value = "/login",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Customer checkLoginDetail(@RequestBody Customer customer){
		Customer ct = null;
		if((ct = loginDao.checkLoginDetail(customer)) != null)
			return ct;
		else
			return new Customer();
	}
	
	@RequestMapping(value = "/signup",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Customer insertSignupDetail(@RequestBody Customer customer) throws Exception{
		if(signupDao.insertCustomer(customer))
			return customer;
		else
			return new Customer();
		
	}
	
	@RequestMapping(value="/items",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Items> getItems(){
		return(itemsDao.getItemsList());
	}
}