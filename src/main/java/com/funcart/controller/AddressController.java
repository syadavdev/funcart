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

<<<<<<< HEAD:src/main/java/com/funcart/controller/ShippingController.java
import com.funcart.dao.ShippingDao;
import com.funcart.dao.service.ShippingService;
=======
import com.funcart.Dao.AddressDao;
import com.funcart.Dao.service.AddressService;

>>>>>>> 3533d470799a8d7f324a1d44b66f8ff657dafe34:src/main/java/com/funcart/controller/AddressController.java
import com.funcart.domain.Customer;
import com.funcart.domain.dto.LoginDto;
import com.funcart.domain.dto.AddressDto;
import com.funcart.domain.dto.SignupDto;

@RestController
public class AddressController {

	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	@SuppressWarnings("unused")
	@Autowired
	private AddressDao shipDao;

	@Autowired
	private AddressService shippingService;
	
	@SuppressWarnings({ "rawtypes", "static-access" })

	@RequestMapping(value = "/address",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveShippingDetail(@RequestBody AddressDto shippingDto) throws Exception {
		AddressDto shippingDtoObj = null;

		try {
			if (shippingService.saveCustomer(shippingDto)) {
				httpStatus = httpStatus.OK;
				shippingDtoObj = shippingDto;
			} else
			{
				httpStatus = httpStatus.EXPECTATION_FAILED;
				return new ResponseEntity<AddressDto>(shippingDto, httpStatus);
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
			shippingDtoObj = new AddressDto();
		}

		return new ResponseEntity<AddressDto>(shippingDtoObj, httpStatus);
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
