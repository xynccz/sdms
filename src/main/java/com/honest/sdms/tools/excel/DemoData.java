package com.honest.sdms.tools.excel;

import com.alibaba.excel.annotation.ExcelProperty;

public class DemoData {
	@ExcelProperty("user")
	private String user;
	@ExcelProperty("password")
	private String password;
	@ExcelProperty("adress")
	private String adress;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
}
