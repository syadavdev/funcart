package com.funcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.service.AddressService;
import com.funcart.domain.dto.AddressDto;
import com.funcart.domain.dto.error.ErrorResponse;
import com.funcart.domain.dto.error.SuccessResponse;
import com.funcart.validator.Validator;

@RestController
public class AddressController {

	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	@Autowired
	private AddressService addressService;
	
	@SuppressWarnings({ "rawtypes", "static-access" })

	@RequestMapping(value = "/address",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveShippingDetail(@RequestBody AddressDto addressDto) throws Exception {
		
		String errorMsg = "Empty Fields";
		if(StringUtils.isEmpty(addressDto.getBillingAddress()) || StringUtils.isEmpty(addressDto.getBillingAddress()) ||
				StringUtils.isEmpty(addressDto.getEmail())){
			httpStatus = HttpStatus.BAD_REQUEST;
		}else if(!Validator.emailValidate(addressDto.getEmail())){
			httpStatus = HttpStatus.BAD_REQUEST;
			errorMsg = "Invalid Email";
		}else{
			try{
				if(addressService.checkExistingCustomer(addressDto.getEmail())){
					if (addressService.saveCustomer(addressDto)) {
						httpStatus = httpStatus.OK;
						return new ResponseEntity<SuccessResponse>(new SuccessResponse("Address Saved SuccessFully",httpStatus.value()),httpStatus);
					}else{	
						httpStatus = httpStatus.EXPECTATION_FAILED;
						errorMsg = addressService.getErrorMsg();
					}
				}else{
					httpStatus = HttpStatus.NOT_FOUND;
					errorMsg = addressService.getErrorMsg();
				}
			}catch(Exception e){
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorMsg = "Internal Server Error";
			}
		}
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),errorMsg),httpStatus);
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