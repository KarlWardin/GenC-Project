package com.cognizant.account.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.account.exception.InvalidTokenException;
import com.cognizant.account.exception.NotFoundException;
import com.cognizant.account.feign.AuthFeign;
import com.cognizant.account.model.MobileDevice;
import com.cognizant.account.repository.MobileDeviceRepository;

@SpringBootTest
class MobileDeviceServiceTest {
	@InjectMocks
	private MobileDeviceServiceImpl mdService;
	@Mock
	private AuthFeign authFeign;
	@Mock
	private MobileDeviceRepository mdRepo;

	@Test
	void testGetIMEINoByMobileNoAuthorized() throws NotFoundException, InvalidTokenException {
		when(authFeign.getValidity("token", "accNo")).thenReturn("true");
		MobileDevice md = new MobileDevice("accNo", "9999999968", "dummy7");
		when(mdRepo.getById("9999999968")).thenReturn(md);
		try {
			String res = mdService.getIMEINoByMobileNo("9999999968", "accNo", "token");
			assertEquals("dummy7", res);
		} catch (Exception e) {
			fail("not working with correct data");
		}
	}

	@Test
	void testGetIMEINoByMobileNoUnAuthorized() {
		when(authFeign.getValidity("token", "accNo")).thenReturn("false");
		MobileDevice md = new MobileDevice("accNo", "9999999968", "dummy7");
		when(mdRepo.getById("9999999968")).thenReturn(md);
		try {
			mdService.getIMEINoByMobileNo("9999999968", "accNo", "token");
			fail("Working with invalid token");
		} catch (Exception e) {
			assert (e instanceof InvalidTokenException);
		}
	}

	@Test
	void testGetMobileDevicesByAccountNoAuthorized() throws NotFoundException, InvalidTokenException {
		when(authFeign.getValidity("token", "accNo")).thenReturn("true");
		List<String> dummyls = new ArrayList<>();
		dummyls.add("9999999968");
		dummyls.add("9999999963");
		when(mdRepo.findAllByAccountNo("accNo")).thenReturn(dummyls);
		try {
			List<String> res = mdService.getMobileDevicesByAccountNo("accNo", "token");
			assertEquals(dummyls, res);
		} catch (Exception e) {
			fail("not working with correct data");
		}
	}

	@Test
	void testGetMobileDevicesByAccountNoUnAuthorized() throws NotFoundException, InvalidTokenException {
		when(authFeign.getValidity("token", "accNo")).thenReturn("false");
		List<String> dummyls = new ArrayList<>();
		dummyls.add("9999999968");
		dummyls.add("9999999963");
		when(mdRepo.findAllByAccountNo("accNo")).thenReturn(dummyls);
		try {
			mdService.getMobileDevicesByAccountNo("accNo", "token");
			fail("working with invalid token");
		} catch (Exception e) {
			assert (e instanceof InvalidTokenException);
		}
	}
}
