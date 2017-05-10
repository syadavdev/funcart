package com.funcart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.service.ItemService;
import com.funcart.domain.dto.ItemDto;

@RestController
public class ItemController {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private ItemService itemService;
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@RequestMapping(value="/items",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getItems(){
		List<ItemDto> itemListDto = new ArrayList<ItemDto>();
		try{
			if((itemListDto = itemService.getList()).isEmpty())
				httpStatus = httpStatus.NOT_FOUND;
			else
				httpStatus = httpStatus.OK;
		}catch(Exception e){
			httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<ItemDto>>(itemListDto,httpStatus);
	}
}
