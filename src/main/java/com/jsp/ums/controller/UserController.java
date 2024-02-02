package com.jsp.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody UserRequest userRequest) {
		return userService.saveUser(userRequest);
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@PathVariable int userId,
			@RequestBody UserRequest userRequest){
		return userService.updateUser(userId, userRequest);
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId){
		return userService.deleteUser(userId);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> getUser(@PathVariable int userId){
		return userService.getUser(userId);
	}
	
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<UserResponse>>> getUsers(){
		return userService.getUsers();
	}

}
