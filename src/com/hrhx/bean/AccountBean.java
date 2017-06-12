package com.hrhx.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName ="orm_account")
public class AccountBean {
	//主键
	@DatabaseField(generatedId = true)
	private int id;
	//账户
	@DatabaseField(columnName = "name")
	private String name;
	//密码
	@DatabaseField(canBeNull = false,columnName = "password")
	private String password;
	//金额
	@DatabaseField(canBeNull = false,columnName = "cash")
	private Double cash;
	 
	public AccountBean() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	} 
}
