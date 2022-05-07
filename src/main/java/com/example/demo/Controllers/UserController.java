package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.UserModel;
import com.example.demo.model.UserModelFake;
import com.example.demo.model.UserModelFakeTransfrom;
import com.example.demo.model.UserModelInteface;
import com.example.demo.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
	@Autowired
	private CustomUserDetailsService userService;

	@GetMapping("/test")
	@PostAuthorize("@mySecurityService.Access('VIEW')")
	public String getTest(@RequestHeader HttpHeaders requestHeader) {
			return "TEST !";
	}
	@GetMapping("/user")
	public Object getAllUser(@RequestHeader HttpHeaders requestHeader) {
		List<UserModelFake> userInfos = userService.getAllUserInfo();
		if (userInfos == null || userInfos.isEmpty()) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return userInfos;
	}

	// @PostMapping("/user")
	// public User addUser(@RequestBody User userRecord) {
	// 	return userService.addUser(userRecord);
	// }

	// @PutMapping("/user/{id}")
	// public User updateUser(@RequestBody User userRecord, @PathVariable Integer id) {
	// 	return userService.updateUser(id,userRecord);
	// }
	
	// @PutMapping("/user/changePassword/{id}")
	// public User updateUserPassword(@RequestBody User userRecord, @PathVariable Integer id) {
	// 	return userService.updatePassword(id,userRecord);
	// }
	
	// @PutMapping("/user/changeRole/{id}")
	// public User updateUserRole(@RequestBody User userRecord, @PathVariable Integer id) {
	// 	return userService.updateRole(id,userRecord);
	// }

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
		try {
			Optional<UserModel> userInfo = userService.getUserInfoById(id);
			if (userInfo == null) {
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}
			userService.remove(id);
			return new ResponseEntity<Object>(userInfo, HttpStatus.OK);
		  } catch (Exception e) {
			return new ResponseEntity<Object>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable Long id) {
		Optional<UserModel> userInfo = userService.getUserInfoById(id);
		if (userInfo == null) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(userInfo, HttpStatus.OK);
	}
}
