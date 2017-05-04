package com.funcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.Dao.ShippingDao;
import com.funcart.Dao.service.ShippingService;

import com.funcart.domain.Customer;
import com.funcart.domain.dto.LoginDto;
import com.funcart.domain.dto.ShippingDto;
import com.funcart.domain.dto.SignupDto;

@RestController
public class ShippingController {

	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	@SuppressWarnings("unused")
	@Autowired
	private ShippingDao shipDao;

	@Autowired
	private ShippingService shippingService;
	
	@SuppressWarnings({ "rawtypes", "static-access" })

	@RequestMapping(value = "/shipping",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveShippingDetail(@RequestBody ShippingDto shippingDto) throws Exception {
		ShippingDto shippingDtoObj = null;

		try {
			if (shippingService.saveCustomer(shippingDto)) {
				httpStatus = httpStatus.CREATED;
				shippingDtoObj = shippingDto;
			} else
			{
				httpStatus = httpStatus.EXPECTATION_FAILED;
				return new ResponseEntity<ShippingDto>(shippingDto, httpStatus);
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
			shippingDtoObj = new ShippingDto();
		}

		return new ResponseEntity<ShippingDto>(shippingDtoObj, httpStatus);
	}

}

/*
 * @RequestMapping(value = "/shipping",method=RequestMethod.POST,
 * consumes=MediaType.APPLICATION_JSON_VALUE) public ResponseEntity
 * saveCustomer(@RequestBody ShippingDto shipDto) throws Exception{ ShippingDto
 * shippingDtoObj = null; try{ if((shippingDtoObj =
 * ShippingDao.saveCustomer(shipDto)) != null){ httpStatus =
 * httpStatus.ACCEPTED; } else{ httpStatus = httpStatus.UNAUTHORIZED;
 * shippingDtoObj = new Customer(); } }catch(Exception e){ httpStatus =
 * httpStatus.EXPECTATION_FAILED; ct = new Customer(); }
 * 
 * return new ResponseEntity<Customer>(shippingDtoObj,httpStatus); }
 */
