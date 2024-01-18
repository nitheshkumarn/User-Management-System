package com.jsp.usm.User_Management.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.usm.User_Management.entity.User;
import com.jsp.usm.User_Management.exception.UserInsertionFailedException;
import com.jsp.usm.User_Management.exception.UserNotFoundByIdException;
import com.jsp.usm.User_Management.repository.UserRepository;
import com.jsp.usm.User_Management.requestdto.UserRequest;
import com.jsp.usm.User_Management.responsedto.UserResponse;
import com.jsp.usm.User_Management.service.UserService;
import com.jsp.usm.User_Management.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ResponseStructure<User> rs;
	
	@Autowired
	private ResponseStructure<UserResponse> rsu;
	
	@Autowired
	private ResponseStructure<List<UserResponse>> rsua;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest) {

		User user = userRepository.save(mapToUser(userRequest));
		rsu.setStatus(HttpStatus.CREATED.value());
		rsu.setMessage("user data inserted successfully");
		rsu.setData(mapToUserResponse(user));

		return new ResponseEntity<ResponseStructure<UserResponse>>(rsu, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(Integer userId, UserRequest userRequest) {
		User user = mapToUser(userRequest);
		User foundUser = userRepository.findById(userId)
				.map(u-> {
					user.setUserId(userId);
					return userRepository.save(user);
				})
				.orElseThrow(() -> new UserInsertionFailedException("User Not Found"));
//		
//		user.setUserId(userId);
//		foundUser = userRepository.save(user);

	
		rsu.setStatus(HttpStatus.OK.value());
		rsu.setMessage("user data UPDATED successfully");
		rsu.setData(mapToUserResponse(user));

		return new ResponseEntity<ResponseStructure<UserResponse>>(rsu, HttpStatus.OK);

	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(Integer userId) {
		
		User foundUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User Not Found"));
		
		userRepository.deleteById(userId);
		
		rsu.setStatus(HttpStatus.OK.value());
		rsu.setMessage("user data DELETED successfully");
		rsu.setData(mapToUserResponse(foundUser));

		return new ResponseEntity<ResponseStructure<UserResponse>>(rsu, HttpStatus.OK);
	}
	


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUser(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User Not Found"));
		
		rsu.setStatus(HttpStatus.FOUND.value());
		rsu.setMessage("user data Fetched successfully");
		rsu.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(rsu, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser() {
		List<User> usersList = (List<User>) userRepository.findAll();
		
		rsua.setStatus(HttpStatus.FOUND.value());
		rsua.setMessage("All users data Fetched successfully");
		rsua.setData(mapToUserResponse(usersList));
		return new ResponseEntity<ResponseStructure<List<UserResponse>>>(rsua, HttpStatus.FOUND);
	}

	private List<UserResponse> mapToUserResponse(List<User> usersList) {
		List<UserResponse> ur = new ArrayList<>();
		
		for(User u : usersList) {
			ur.add(mapToUserResponse(u));
		}
		return ur;
	}
	
	private User mapToUser(UserRequest userRequest) {
		
		return User.builder()
				.userName(userRequest.getUserName())
				.userEmail(userRequest.getUserEmail())
				.userPass(userRequest.getUserPass())
				.build();
		
	}

	private UserResponse mapToUserResponse(User user) {
		
		return UserResponse.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.userEmail(user.getUserEmail())
				.build();
	}


}
