package com.cognizant.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserData {
	@Id
	@Column(name = "accountno", length = 20)
	private String accountNo;
	@Column(name = "password", length = 20)
	private String password;
	
	public UserData() {}

	public UserData(String accountNo, String password) {
		super();
		this.accountNo = accountNo;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserData [accountNo=" + accountNo + ", password=" + password + "]";
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
