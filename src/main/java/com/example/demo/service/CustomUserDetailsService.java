package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public List<UserModel> getAllActiveUserInfo() {
		return userRepository.findAllByEnabled((short) 1);
	}

	public Optional<UserModel> getUserInfoById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String input) {
		UserModel user = userRepository.findByUsername(input);

		if (user == null)
			throw new BadCredentialsException("Bad credentials");

		new AccountStatusUserDetailsChecker().check(user);

		return user;
	}
	
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
	
}
