package com.jsp.usm.User_Management.controller;

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


import com.jsp.usm.User_Management.requestdto.UserRequest;
import com.jsp.usm.User_Management.responsedto.UserResponse;
import com.jsp.usm.User_Management.service.UserService;
import com.jsp.usm.User_Management.util.ResponseStructure;

import jakarta.validation.Valid;




@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody @Valid UserRequest userRequest) {
		ResponseEntity<ResponseStructure<UserResponse>> rs = userService.saveUser(userRequest);
		return rs;
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@PathVariable Integer userId,
			@RequestBody UserRequest userRequest) {
		ResponseEntity<ResponseStructure<UserResponse>> updateUser = null;

		updateUser = userService.updateUser(userId, userRequest);

		return updateUser;
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable Integer userId){
			ResponseEntity<ResponseStructure<UserResponse>> deleteUser = null;

			deleteUser = userService.deleteUser(userId);

			return deleteUser;
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUser(@PathVariable Integer userId){
		ResponseEntity<ResponseStructure<UserResponse>> findUser = null;

		findUser = userService.findUser(userId);

		return findUser;
	}
	
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUsers(){
		ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser = null;
		
		findAllUser = userService.findAllUser();
		
		return findAllUser;
	}
	}
		

