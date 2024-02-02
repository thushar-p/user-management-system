package com.jsp.ums.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.util.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest);

	ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userId, UserRequest userRequest);

	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);

	ResponseEntity<ResponseStructure<UserResponse>> getUser(int userId);

	ResponseEntity<ResponseStructure<List<UserResponse>>> getUsers();

}
