package com.cognizant.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.account.exception.InvalidTokenException;
import com.cognizant.account.exception.NotFoundException;
import com.cognizant.account.feign.AuthFeign;
import com.cognizant.account.repository.MobileDeviceRepository;

@Service
public class MobileDeviceServiceImpl implements MobileDeviceService {

	@Autowired
	private MobileDeviceRepository mdRepo;
	@Autowired
	private AuthFeign authFeign; 

	@Override
	public String getIMEINoByMobileNo(final String mobileNo, final String accountNo, final String token)
			throws NotFoundException, InvalidTokenException {
		if (this.authFeign.getValidity(token, accountNo).equals("true")) {
			try {
				return mdRepo.getById(mobileNo).getImeiNo();
			} catch (Exception e) {
				throw new NotFoundException(e.getMessage());
			}
		}
		throw new InvalidTokenException("Account Not Logged In !!");
	}

	@Override
	public List<String> getMobileDevicesByAccountNo(final String accountNo, final String token)
			throws NotFoundException, InvalidTokenException {
		if (this.authFeign.getValidity(token, accountNo).equals("true")) {
			try {
				return mdRepo.findAllByAccountNo(accountNo);
			} catch (Exception e) {
				throw new NotFoundException(e.getMessage());
			}
		}
		throw new InvalidTokenException("Account Not Logged In !!");

	}

}
