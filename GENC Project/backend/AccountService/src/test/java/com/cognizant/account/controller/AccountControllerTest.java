package com.cognizant.account.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.account.exception.InvalidTokenException;
import com.cognizant.account.exception.NotFoundException;
import com.cognizant.account.service.MobileDeviceService;

@SpringBootTest
class AccountControllerTest {
	
	@InjectMocks
	private AccountController authController;
	@Mock
	private MobileDeviceService mdService;

	@Test
	void testGetImeiNo() throws NotFoundException, InvalidTokenException {
		when(mdService.getIMEINoByMobileNo("9193859395" ,"accNo" ,"token")).thenReturn("dummy7");
		assertEquals("dummy7",authController.getImeiNo("9193859395", "accNo", "token"));
	}

	@Test
	void testGetMobileDevices() throws NotFoundException, InvalidTokenException {
		List<String> ls = new ArrayList<>();
		ls.add("9275873464");
		ls.add("9448598645");
		when(mdService.getMobileDevicesByAccountNo("accNo","token")).thenReturn(ls);
		assertEquals(ls,authController.getMobileDevices("accNo", "token"));
	}

}
