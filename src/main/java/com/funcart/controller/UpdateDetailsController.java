package com.funcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.service.ChangeEmailPasswordService;
import com.funcart.dao.service.UpdateCustomerService;
import com.funcart.domain.dto.Response.ErrorResponse;
import com.funcart.domain.dto.Response.SuccessResponse;
import com.funcart.domain.dto.customer.ChangeEmailDto;
import com.funcart.domain.dto.customer.ChangePasswordDto;
import com.funcart.domain.dto.customer.UpdateCustomerDto;
import com.funcart.utility.JWTTokenGenerator;
import com.funcart.validator.Validator;

@RestController
public class UpdateDetailsController {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private UpdateCustomerService updateCustomerService;
	
	@Autowired
	private ChangeEmailPasswordService emailPasswordService;
	
	@Autowired
	private JWTTokenGenerator jwt;
		
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/updateDetails",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateDetails(@RequestBody UpdateCustomerDto updateCustomerDto,@RequestHeader String token,@RequestHeader String secret){
		String errorMsg = "Empty Fields";
		if(!jwt.parseJWT(token,secret)){
			httpStatus = HttpStatus.UNAUTHORIZED;
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),"Token Time Expire"),httpStatus);
		}else if(StringUtils.isEmpty(updateCustomerDto.getEmail()) || StringUtils.isEmpty(updateCustomerDto.getPassword())){
			httpStatus = HttpStatus.BAD_REQUEST;
			errorMsg = "Empty Email And Password";
		}else if(StringUtils.isEmpty(updateCustomerDto.getNewBillingAddress()) && StringUtils.isEmpty(updateCustomerDto.getNewShippingAddress())
				 && StringUtils.isEmpty(updateCustomerDto.getNewName()) && updateCustomerDto.getNewPhoneNumber() == 0L){
			httpStatus = HttpStatus.BAD_REQUEST;
			errorMsg = "Nothing To Update";
		}else if(!updateCustomerService.validateNewDetails(updateCustomerDto)){
			httpStatus = HttpStatus.BAD_REQUEST;
			errorMsg = updateCustomerService.getErrorMsg();
		}else if(updateCustomerService.checkPhoneNumber(updateCustomerDto.getNewPhoneNumber())){
			httpStatus = HttpStatus.BAD_REQUEST;
			errorMsg = updateCustomerService.getErrorMsg();
		}
		else{
			try{
				if(updateCustomerService.checkExistingCustomer(updateCustomerDto)){
					if(updateCustomerService.makingQuery(updateCustomerDto)){
						if(updateCustomerService.updateCustomer(updateCustomerDto)){
							httpStatus = HttpStatus.OK;
							return new ResponseEntity<SuccessResponse>(new SuccessResponse("Details Update SuccessFully",httpStatus.value()),httpStatus);
						}else{	
							httpStatus = HttpStatus.EXPECTATION_FAILED;
							errorMsg = updateCustomerService.getErrorMsg();
						}
					}else{
						httpStatus = HttpStatus.EXPECTATION_FAILED;
						errorMsg = "Error In Making Query";
					}
				}else{
					httpStatus = HttpStatus.NOT_FOUND;
					errorMsg = updateCustomerService.getErrorMsg();
				}
			}catch(Exception e){
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorMsg = "Internal Server Error";
			}
		}
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),errorMsg),httpStatus);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/changeEmail",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity changeEmailDto(@RequestBody ChangeEmailDto changeEmailDto,@RequestHeader String token,@RequestHeader String secret){
		String errorMsg = "Empty Fields Details";
		if(!jwt.parseJWT(token,secret)){
			httpStatus = HttpStatus.UNAUTHORIZED;
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),"Token Time Expire"),httpStatus);
		}else if(StringUtils.isEmpty(changeEmailDto.getPassword()) || StringUtils.isEmpty(changeEmailDto.getNewEmail())
			|| StringUtils.isEmpty(changeEmailDto.getOldEmail())){
			httpStatus = HttpStatus.BAD_REQUEST;
		}else if(!Validator.emailValidate(changeEmailDto.getOldEmail()) || !Validator.emailValidate(changeEmailDto.getNewEmail())
			|| !Validator.passwordValidate(changeEmailDto.getPassword())){
			httpStatus = HttpStatus.BAD_REQUEST;
			errorMsg = "Invalid Fields Details";
		}else if(emailPasswordService.checkExistingEmail(changeEmailDto.getNewEmail())){
			httpStatus = HttpStatus.BAD_REQUEST;
			errorMsg = "New Email Alreay Exist";
		}else{
			try{
				if(emailPasswordService.checkExistingCustomer(changeEmailDto.getOldEmail(),changeEmailDto.getPassword())){
					if(emailPasswordService.changeEmail(changeEmailDto)){
						httpStatus = HttpStatus.OK;
						return new ResponseEntity<SuccessResponse>(new SuccessResponse("Email Change SuccessFully",httpStatus.value()),httpStatus);
					}else{
						httpStatus = HttpStatus.EXPECTATION_FAILED;
						errorMsg = emailPasswordService.getErrorMsg();
					}
				}else{
					httpStatus = HttpStatus.NOT_FOUND;
					errorMsg = emailPasswordService.getErrorMsg();
				}
			}catch(Exception e){
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorMsg = "Internal Server Error";
			}
		}
		
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),errorMsg),httpStatus);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/changePassword",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity changePasswordDto(@RequestBody ChangePasswordDto changePasswordDto,@RequestHeader String token,@RequestHeader String secret){
		String errorMsg = "Empty Fields Details";
		if(!jwt.parseJWT(token,secret)){
			httpStatus = HttpStatus.UNAUTHORIZED;
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),"Token Time Expire"),httpStatus);
		}else if(StringUtils.isEmpty(changePasswordDto.getNewPassword()) || StringUtils.isEmpty(changePasswordDto.getOldPassword())
			|| StringUtils.isEmpty(changePasswordDto.getEmail())){
			httpStatus = HttpStatus.BAD_REQUEST;
		}else if(!Validator.emailValidate(changePasswordDto.getEmail()) || !Validator.passwordValidate(changePasswordDto.getNewPassword())
			|| !Validator.passwordValidate(changePasswordDto.getOldPassword())){
			httpStatus = HttpStatus.BAD_REQUEST;
			errorMsg = "Invalid Fields Details";
		}else{
			try{
				if(emailPasswordService.checkExistingCustomer(changePasswordDto.getEmail(),changePasswordDto.getOldPassword())){
					if(emailPasswordService.changePassword(changePasswordDto)){
						httpStatus = HttpStatus.OK;
						return new ResponseEntity<SuccessResponse>(new SuccessResponse("Password Change SuccessFully",httpStatus.value()),httpStatus);
					}else{
						httpStatus = HttpStatus.EXPECTATION_FAILED;
						errorMsg = emailPasswordService.getErrorMsg();
					}
				}else{
					httpStatus = HttpStatus.NOT_FOUND;
					errorMsg = emailPasswordService.getErrorMsg();
				}
			}catch(Exception e){
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorMsg = "Internal Server Error";
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),errorMsg),httpStatus);
	}
}
