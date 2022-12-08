package com.cognizant.account.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.account.service.MobileDeviceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(produces = "application/json", value = "API srvice that manages account and mobile devices info")
@CrossOrigin
public class AccountController {

	@Autowired
	private MobileDeviceService mdService;
	Logger logger = LoggerFactory.getLogger(AccountController.class);

	@ApiOperation(value = "returns IMEI number for mobile number", response = String.class, notes = "Customer must be authenticated")
	@GetMapping("/getImeiNo")
	public String getImeiNo(@RequestParam("mobileNo") String mobileNo, @RequestParam("accountNo") String accountNo,
			@RequestHeader("Authorization") final String token) {
		String res = "";
		try {
			res = mdService.getIMEINoByMobileNo(mobileNo, accountNo, token);
			logger.trace(res);
		} catch (Exception e) {
			logger.error(e.getMessage());
			res = e.getMessage();
		}
		return res;
	}

	@ApiOperation(value = "returns all the mobile numbers for a particuler authenticated customer", response = List.class, notes = "Customer must be authenticated")
	@GetMapping("/getMobileNo")
	public List<String> getMobileDevices(@RequestParam("accountNo") String accountNo,
			@RequestHeader("Authorization") final String token) {
		List<String> res = new ArrayList<>();
		try {
			res = mdService.getMobileDevicesByAccountNo(accountNo, token);
			logger.trace(res.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return res;
	}
}
