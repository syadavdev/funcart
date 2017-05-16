package com.funcart.controller;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.service.ItemService;
import com.funcart.domain.dto.ItemDto;
import com.funcart.domain.dto.error.ErrorResponse;

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
}
