package com.funcart.controller;

import java.util.List;

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

import com.funcart.dao.service.ItemService;
import com.funcart.domain.dto.AddItemListDto;
import com.funcart.domain.dto.DeleteItemListDto;
import com.funcart.domain.dto.ItemDto;
import com.funcart.domain.dto.Response.ErrorResponse;
import com.funcart.domain.dto.Response.SuccessResponse;
import com.funcart.validator.Validator;

@RestController
public class ItemController {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private ItemService itemService;
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value="/items",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getItems(){
		String errorMsg = "Empty Item List";
		try{
			if(itemService.getList()){
				httpStatus = httpStatus.OK;
				return new ResponseEntity<List<ItemDto>>(itemService.getItemDtoList(),httpStatus);
			}else{
				httpStatus = httpStatus.NOT_FOUND;
				errorMsg = "Items Retrieving Error";
			}
		}catch(NoResultException e){
			httpStatus = httpStatus.NOT_FOUND;
			errorMsg = "Not Found";
		}catch(Exception e){
			httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
			errorMsg = "Internal Server Error";			
		}
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),errorMsg),httpStatus);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/addItems",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addItems(@RequestBody AddItemListDto addItemListDto){
		String errorMsg = "Empty Item Fields";
		if(StringUtils.isEmpty(addItemListDto.getSellerPasscode()) || (addItemListDto.getItemDtoList()).isEmpty()){
			httpStatus = HttpStatus.BAD_REQUEST;
		}else if(!Validator.passwordValidate(addItemListDto.getSellerPasscode()) || !Validator.nameValidate(addItemListDto.getSellerName())){
			httpStatus = HttpStatus.BAD_REQUEST;
			errorMsg = "Invalid SellerName Or Passcode";
		}else if(!addItemListDto.getSellerName().equals("sandeep yadav") || !addItemListDto.getSellerPasscode().equals("sandeepyadav")){
			httpStatus = HttpStatus.NOT_FOUND;
			errorMsg = "Not Found SellerName Or Passcode";			
		}else{
			try{
				if(itemService.checkItemList(addItemListDto.getItemDtoList())){
					httpStatus = HttpStatus.BAD_REQUEST;
					errorMsg = itemService.getErrorMsg();
				}else{
					if(itemService.insertItemList(addItemListDto.getItemDtoList())){
						httpStatus = HttpStatus.OK;
						return new ResponseEntity<SuccessResponse>(new SuccessResponse("Items Add SuccessFully", httpStatus.value()),httpStatus);
					}else{
						httpStatus = HttpStatus.EXPECTATION_FAILED;
						errorMsg = itemService.getErrorMsg();
					}
				}
			}catch(Exception e){
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorMsg = "Internal Server Error";
			}
		}
		
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),errorMsg),httpStatus);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/deleteItems",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteItems(@RequestBody DeleteItemListDto deleteItemListDto){
		String errorMsg = "Empty Fields Details";
		if(StringUtils.isEmpty(deleteItemListDto.getSellerPasscode()) || (deleteItemListDto.getItemNameList()).isEmpty()){
			httpStatus = HttpStatus.BAD_REQUEST;
		}else if(!Validator.passwordValidate(deleteItemListDto.getSellerPasscode()) || !Validator.nameValidate(deleteItemListDto.getSellerName())){
			httpStatus = HttpStatus.BAD_REQUEST;
			errorMsg = "Invalid SellerName Or Passcode";
		}else if(!deleteItemListDto.getSellerName().equals("sandeep yadav") || !deleteItemListDto.getSellerPasscode().equals("sandeepyadav")){
			httpStatus = HttpStatus.NOT_FOUND;
			errorMsg = "Not Found SellerName Or Passcode";			
		}else{
			try{
				if(itemService.checkItemNames(deleteItemListDto.getItemNameList())){
					if(itemService.deleteItemList(deleteItemListDto.getItemNameList())){
						httpStatus = HttpStatus.OK;
						return new ResponseEntity<SuccessResponse>(new SuccessResponse("Items Deleted Successfully",httpStatus.value()),httpStatus);
					}else{
						httpStatus = HttpStatus.BAD_REQUEST;
						errorMsg = itemService.getErrorMsg();
					}
				}else{
					httpStatus = HttpStatus.BAD_REQUEST;
					errorMsg = itemService.getErrorMsg();
				}
			}catch(Exception e){
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorMsg = "Internal Server Error";
			}
		}
		
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),errorMsg),httpStatus);
	}
}
