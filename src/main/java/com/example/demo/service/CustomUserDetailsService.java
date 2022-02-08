package com.example.demo.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.model.UserModel;
import com.example.demo.model.UserModelFake;
import com.example.demo.model.UserModelFakeTransfrom;
import com.example.demo.model.UserModelInteface;
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

	public List<UserModelFakeTransfrom> getAllUserInfo() {
		List<UserModelFake> test1 = userRepository.findAllNativeQuery2();
		return userRepository.findAllNativeQuery().stream()
		.map(UserModelFakeTransfrom::from)
		.collect(Collectors.toList());
	}

	public List<UserModel> getAllActiveUserInfo() {
		return userRepository.findAllByEnabled((short) 1);
	}

	public List<UserModel> getAllUserMybatist() {
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
