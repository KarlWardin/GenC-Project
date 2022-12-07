package com.cognizant.account;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountServiceApplicationTests {

	@Test
	void main() {
		AccountServiceApplication.main(new String[] {});
		assertTrue(true);
	}

}
