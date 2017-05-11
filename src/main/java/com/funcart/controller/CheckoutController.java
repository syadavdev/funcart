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


import com.funcart.dao.service.CheckoutService;

import com.funcart.domain.Customer;
import com.funcart.domain.Item;
import com.funcart.domain.dto.AddressDto;
import com.funcart.domain.dto.CheckoutDto;

import com.funcart.domain.dto.SignupDto;

@RestController
public class CheckoutController {

	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	

	@Autowired
	private CheckoutService checkService;

	@SuppressWarnings({ "rawtypes", "static-access" })

	@RequestMapping(value = "/checkot", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity savePayment(@RequestBody CheckoutDto checkDto) throws Exception {
		CheckoutDto checkObj = null;

		try {

			if (checkService.checkCustomer(checkDto)) {
				httpStatus = httpStatus.OK;
				checkObj = checkDto;
			} else {
				httpStatus = httpStatus.EXPECTATION_FAILED;
				return new ResponseEntity<CheckoutDto>(checkDto, httpStatus);
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
			checkObj = new CheckoutDto();
		}

		return new ResponseEntity<CheckoutDto>(checkObj, httpStatus);
	}

}
