package com.cognizant.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.auth.model.UserData;
import com.cognizant.auth.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@Api(produces = "application/text", value = "Authentication Service")
public class AuthController {
	@Autowired
	private AuthService authService;

	Logger logger = LoggerFactory.getLogger(AuthController.class);

	// returns jwt token
	@ApiOperation(value = "Verify the Credentials and generate Jwt Token", response = ResponseEntity.class)
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody UserData userData) {
		logger.trace(userData.toString());
		String token = authService.login(userData);
		logger.trace(token);
		return new ResponseEntity<>(token, HttpStatus.OK);
	}

	// returns if the token is valid
	@ApiOperation(value = "validate Jwt Token", response = ResponseEntity.class)
	@GetMapping("/validate")
	public ResponseEntity<Object> validate(@RequestHeader("Authorization") final String jwtToken,
			@RequestParam String accountNo) {
		String token = jwtToken.substring(7); // jwtToken = "Bearer " + token
		Boolean isValid = authService.validate(token, accountNo);
		logger.trace(token + " is " + isValid);
		return new ResponseEntity<>(isValid, HttpStatus.OK);
	}
}
