package com.cognizant.account.service;

import java.util.List;

import com.cognizant.account.exception.InvalidTokenException;
import com.cognizant.account.exception.NotFoundException;

public interface MobileDeviceService {

	public String getIMEINoByMobileNo(String mobileNo,String accountNo,String token) throws NotFoundException,InvalidTokenException;

	public List<String> getMobileDevicesByAccountNo(String accountNo,String token) throws NotFoundException,InvalidTokenException;
	
}
