package com.funcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.service.ItemService;
import com.funcart.domain.dto.ErrorResponse;
import com.funcart.domain.dto.ItemDto;

@RestController
public class ItemController {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private ItemService itemService;
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value="/items",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getItems(){
		try{
			if(itemService.getList()){
				httpStatus = httpStatus.OK;
			}else{
				httpStatus = httpStatus.NOT_FOUND;
				return new ResponseEntity<ErrorResponse>(new ErrorResponse(404, "Empty Item List"), httpStatus);
			}
		}catch(Exception e){
			httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(404, "Internal Server Error"), httpStatus);			
		}
		
		return new ResponseEntity<List<ItemDto>>(itemService.getItemDtoList(),httpStatus);
	}
}
