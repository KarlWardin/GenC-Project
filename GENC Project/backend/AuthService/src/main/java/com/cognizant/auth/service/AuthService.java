package com.cognizant.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.auth.model.UserData;
import com.cognizant.auth.repository.AuthRepository;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private AuthRepository authRepo;
	@Autowired
	private JwtUtil jwtUtil;

	public String login(UserData user) {
		System.out.print(user);
		try {
			final UserDetails userDetails = loadUserByUsername(user.getAccountNo());
			if (userDetails.getPassword().equals(user.getPassword())) {
				String jwtToken = jwtUtil.generateJwtToken(userDetails);
				return jwtToken;
			} else {
				return "Incorrect Credentials";
			}
		} catch (Exception e) {
			return "Incorrect Credentials " + e.getMessage();
		}
	}

	public Boolean validate(String token, String accountNo) {
		return jwtUtil.validateJwtToken(token, accountNo);
	}

	@Override
	public UserDetails loadUserByUsername(String accountNo) throws UsernameNotFoundException {
		System.out.print(accountNo);
		try {
			UserData user = (UserData) authRepo.findById(accountNo).orElse(null);
			return new User(user.getAccountNo(), user.getPassword(), new ArrayList<>());
		} catch (Exception e) {
			throw new UsernameNotFoundException("User Not Found");
		}
	}

}
