package com.funcart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Items{

	@Id @GeneratedValue
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String picName;

	public String getPicName() {
		return picName;
	}

	public int getId() {
		return id;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getName() {
		return name;
	}
}
