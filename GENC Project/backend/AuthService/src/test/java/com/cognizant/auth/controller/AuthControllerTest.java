package com.cognizant.auth.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.cognizant.auth.model.UserData;
import com.cognizant.auth.service.AuthService;

@SpringBootTest
class AuthControllerTest {
	@Autowired
	AuthController authController;
	UserData userData;

	@Test
	void testLoginPassed() {
		userData = new UserData("ABC002", "ABC002");
		String token = authController.login(userData).getBody().toString();
		assertFalse(token.startsWith("Incorrect"));
	}

	@Test
	void testLoginFailed() {
		userData = new UserData("ABC002", "ABC003");
		String token = authController.login(userData).getBody().toString();
		assertTrue(token.startsWith("Incorrect"));
	}

	@Test
	void testValidatePassed() {
		userData = new UserData("ABC002", "ABC002");
		String token = "Bearer " + authController.login(userData).getBody().toString();
		assertEquals("true", authController.validate(token, "ABC002").getBody().toString());
	}

	@Test
	void testValidateFailed() {
		userData = new UserData("ABC002", "ABC002");
		String token = "Bearer " + authController.login(userData).getBody().toString();
		assertEquals("false", authController.validate(token, "ABC003").getBody().toString());
	}

}
