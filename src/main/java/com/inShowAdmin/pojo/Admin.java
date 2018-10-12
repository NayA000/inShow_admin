package com.inShowAdmin.pojo;

import java.io.Serializable;

public class Admin implements Serializable{
	
	private String name = "admin";
	
	private String password = "admin";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
