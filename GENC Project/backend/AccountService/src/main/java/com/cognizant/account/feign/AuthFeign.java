package com.cognizant.account.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${AUTH_APP_NAME}", url = "${AUTH_APP_URL}")
public interface AuthFeign {
    // return "true" if valid , else it'll return "false" as response from "AuthService"
	@RequestMapping(value = { "/validate" }, method = RequestMethod.GET)
	String getValidity(@RequestHeader("Authorization") final String token,@RequestParam("accountNo") String accountNo);
}
