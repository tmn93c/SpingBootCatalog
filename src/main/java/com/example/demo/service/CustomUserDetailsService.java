package com.example.demo.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserModelFake;
import com.example.demo.model.UserModelInteface;
import com.example.demo.repository.UserRepository;

import com.example.demo.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public List<UserModelFake> getAllUserInfo() {
		return userRepository.findAllNativeQuery().stream()
		.map(t -> 
			new UserModelFake(
			    ((BigInteger) (t.get(0))).longValue(),
				t.get(1, String.class), 
				t.get(2, String.class),
				t.get(3, String.class),
				t.get(4, String.class),
				t.get(5, LocalDate.class)
				)
		)
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

		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		UserModel user = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", id)
		);

		return UserPrincipal.create(user);
	}

    public void remove(Long id) {
        userRepository.deleteById(id);
    }
	
}
