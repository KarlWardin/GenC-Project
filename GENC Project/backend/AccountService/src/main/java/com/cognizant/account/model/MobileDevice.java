package com.cognizant.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="devices")
public class MobileDevice {
	@Column(name="accountno")
	private String accountNo;
	@Id
	@Column(name="mobileno")
    private String mobileNo;
	@Column(name="imeino")
    private String imeiNo;
    
	public MobileDevice() {
		super();
	}
    public MobileDevice(String accountNo, String mobileNo, String imeiNo) {
		super();
		this.accountNo = accountNo;
		this.mobileNo = mobileNo;
		this.imeiNo = imeiNo;
	}
	@Override
	public String toString() {
		return "MobileDevice [accountNo=" + accountNo + ", mobileNo=" + mobileNo + ", imeiNo=" + imeiNo + "]";
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getImeiNo() {
		return imeiNo;
	}
	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}
}
