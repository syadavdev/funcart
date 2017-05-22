package com.funcart.dao.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.ImageLinkDao;

@Service
public class ImageLinkService {
	
	@Autowired
	private ImageLinkDao imageLinkDao;
	
	private String errorMsg;
	private String picLink;
	
	public boolean getImageLink(String picName){
		boolean flag = false;
		try{
			if(imageLinkDao.getImageLink(picName)){
				picLink = imageLinkDao.getPicLink();
				flag = true;
			}else{
				errorMsg = "Picture Not_Found";
				flag = false;
			}
		}catch(NoResultException e){
			errorMsg = "Picture Not_Found";
			flag = false;
		}
		return flag;
	}
	
	public String getPicLink() {
		return picLink;
	}
	public void setPicLink(String picLink) {
		this.picLink = picLink;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
