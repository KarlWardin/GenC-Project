package com.cognizant.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cognizant.auth.model.UserData;

@SpringBootTest
class AuthServiceTest {

	@Autowired
	private AuthService authService;
	@Mock
	private JwtUtil jwtUtil;
	UserData userData;
	UserDetails userDetails;
	User user;

	@Test
	void testLoginPassed() {
		userData = new UserData("ABC002", "ABC002");
		String token = authService.login(userData);
		assertNotNull(token);
		assertEquals(false,token.startsWith("Incorrect Credentials"));
	}
	@Test
	void testLoginFailed() {
		userData = new UserData("ABC002", "ABC003");
		String token = authService.login(userData);
		assertNotNull(token);
		assertEquals(true,token.startsWith("Incorrect Credentials"));
	}

	@Test
	void testValidate() {
		userDetails = new User("ABC002", "ABC002", new ArrayList<>());
		String generateToken = jwtUtil.generateJwtToken(userDetails);
		authService.validate(generateToken, "ABC002");
	}

	@Test
	void testLoadUserByUsername() {
		userDetails = authService.loadUserByUsername("ABC002");
		assertEquals("ABC002", userDetails.getPassword());
	}

	@Test
	void testLoadUserByUsernameUserNotFound() {
		UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
			userDetails = authService.loadUserByUsername("ABC010");
		});
		assertEquals("User Not Found", exception.getMessage());
	}

}
