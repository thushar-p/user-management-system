package com.jsp.ums.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.ums.entity.User;
import com.jsp.ums.exception.UserNotFoundByIdException;
import com.jsp.ums.repository.UserRepo;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ResponseStructure<UserResponse> structure;


	private User mapToUser(UserRequest userRequest) {

		return User.builder()
				.userName(userRequest.getUserName())
				.userEmail(userRequest.getUserEmail())
				.userPassword(passwordEncoder.encode(userRequest.getUserPassword()))
				.build();

	}

	private UserResponse mapToUserResponse(User  user) {

		return UserResponse.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.userEmail(user.getUserEmail())
				.build();
	}


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest) {

		User user =  userRepo.save(mapToUser(userRequest));

		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("User saved succesfully");
		structure.setData(mapToUserResponse(user));

		return new ResponseEntity<ResponseStructure<UserResponse>>(structure, HttpStatus.CREATED);
	}



	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userId, UserRequest userRequest) {

		User fetchedUser = userRepo.findById(userId)
				.map(u -> {
					User newUser = mapToUser(userRequest);
					newUser.setUserId(userId);
					return userRepo.save(newUser);
				})
				.orElseThrow(() -> new UserNotFoundByIdException("User not found"));


		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User updated succesfuly!!!");
		structure.setData(mapToUserResponse(fetchedUser));

		return new ResponseEntity<ResponseStructure<UserResponse>>(structure, HttpStatus.OK);
	}



	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId) {

		User fetchedUser = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found"));

		userRepo.deleteById(userId);

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User deleted succesfuly!!!");
		structure.setData(mapToUserResponse(fetchedUser));

		return new ResponseEntity<ResponseStructure<UserResponse>>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> getUser(int userId) {

		User user =  userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found"));

		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("User fetched succesfuly!!!");
		structure.setData(mapToUserResponse(user));

		return new ResponseEntity<ResponseStructure<UserResponse>>(structure, HttpStatus.FOUND);

	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> getUsers() {

		List<User> userList = (List<User>) userRepo.findAll();
		
		List<UserResponse> collect = userList.stream()
				.map(this::mapToUserResponse)
				.collect(Collectors.toList());

		ResponseStructure<List<UserResponse>> structure = new ResponseStructure<List<UserResponse>>();

		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("User fetched succesfuly!!!");
		structure.setData(collect);

		return new ResponseEntity<ResponseStructure<List<UserResponse>>>(structure, HttpStatus.FOUND);

	}


}
