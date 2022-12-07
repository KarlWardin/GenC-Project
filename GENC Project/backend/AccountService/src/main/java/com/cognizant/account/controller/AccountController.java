package com.cognizant.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.account.exception.InvalidTokenException;
import com.cognizant.account.exception.NotFoundException;
import com.cognizant.account.service.MobileDeviceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(produces = "application/json", value = "API srvice that manages account and mobile devices info")
@CrossOrigin
public class AccountController {

	@Autowired
	private MobileDeviceService mdService;

	@ApiOperation(value = "", response = String.class)
	@GetMapping("/getImeiNo")
	public String getImeiNo(@RequestParam String mobileNo , @RequestParam String accountNo , @RequestHeader("Authorization") final String token) throws NotFoundException, InvalidTokenException {
		return mdService.getIMEINoByMobileNo(mobileNo ,accountNo ,token);
	}
	
	@ApiOperation(value = "", response = List.class)
	@GetMapping("/getMobileNo")
	public List<String> getMobileDevices(@RequestParam String accountNo , @RequestHeader("Authorization") final String token) throws NotFoundException, InvalidTokenException {
		return mdService.getMobileDevicesByAccountNo(accountNo,token);
	}
}
