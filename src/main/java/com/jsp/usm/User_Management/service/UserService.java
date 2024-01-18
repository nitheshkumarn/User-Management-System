package com.jsp.usm.User_Management.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.usm.User_Management.entity.User;
import com.jsp.usm.User_Management.requestdto.UserRequest;
import com.jsp.usm.User_Management.responsedto.UserResponse;
import com.jsp.usm.User_Management.util.ResponseStructure;

public interface UserService {

	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest);

	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(Integer userId, UserRequest userRequest);

	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(Integer userId);

	public ResponseEntity<ResponseStructure<UserResponse>> findUser(Integer userId);
	
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser();
	
	

}
