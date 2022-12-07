package com.cognizant.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
class JwtUtilTest {
	@Mock
	UserDetails userDetails;
	@InjectMocks
	JwtUtil jwtUtil;

	@Test
	void testGenerateJwtToken() {
		userDetails = new User("ABC002", "ABC002", new ArrayList<>());
		String generateToken = jwtUtil.generateJwtToken(userDetails);
		assertNotNull(generateToken);
	}

	@Test
	void testValidateJwtTokenWithCorrectCredentials() {
		userDetails = new User("ABC002", "ABC002", new ArrayList<>());
		String generateToken = jwtUtil.generateJwtToken(userDetails);
		Boolean validateToken = jwtUtil.validateJwtToken(generateToken, "ABC002");
		assertEquals(true, validateToken);
	}

	@Test
	void testValidateJwtTokenWithIncorrectCredentials() {
		userDetails = new User("ABC002", "ABC002", new ArrayList<>());
		String generateToken = jwtUtil.generateJwtToken(userDetails);
		Boolean validateToken = jwtUtil.validateJwtToken(generateToken, "ABC003");
		assertEquals(false, validateToken);
	}

	@Test
	void testGetUsernameFromToken() {
		userDetails = new User("ABC002", "ABC002", new ArrayList<>());
		String generateToken = jwtUtil.generateJwtToken(userDetails);
		String accountNo = jwtUtil.getUsernameFromToken(generateToken);
		assertEquals("ABC002", accountNo);
	}

}
