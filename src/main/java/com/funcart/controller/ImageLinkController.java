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

import com.funcart.dao.service.ImageLinkService;
import com.funcart.domain.dto.PicNameDto;
import com.funcart.domain.dto.Response.ErrorResponse;
import com.funcart.validator.Validator;

@RestController
public class ImageLinkController {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private ImageLinkService imageLinkService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/imageLink",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getImageLink(@RequestBody PicNameDto picNameDto){
		String errorMsg = "Empty Fields";
		class PicLink{
			private String picLink;

			@SuppressWarnings("unused")
			public String getPicLink() {
				return picLink;
			}
			public void setPicLink(String picLink) {
				this.picLink = picLink;
			}
		}
		if(StringUtils.isEmpty(picNameDto)){
			httpStatus = HttpStatus.BAD_REQUEST;
		}else if(!Validator.imageNameValidate(picNameDto.getPicName())){
			httpStatus = HttpStatus.BAD_REQUEST;
			errorMsg = "Invalid Input";
		}else{
			try{
				if(imageLinkService.getImageLink(picNameDto.getPicName())){
					httpStatus = HttpStatus.OK;
					PicLink picLinkStr = new PicLink();
					picLinkStr.setPicLink(imageLinkService.getPicLink());
					return new ResponseEntity<PicLink>(picLinkStr,httpStatus);
				}else{
					httpStatus = HttpStatus.NOT_FOUND;
					errorMsg = imageLinkService.getErrorMsg();
				}
			}catch(Exception e){
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorMsg = "Internal Service Error";
				e.printStackTrace();
			}
		}
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),errorMsg),httpStatus);
	}
}
