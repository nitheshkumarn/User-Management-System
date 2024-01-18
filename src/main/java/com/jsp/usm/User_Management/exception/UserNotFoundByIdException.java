package com.jsp.usm.User_Management.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserNotFoundByIdException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;

	@Override
	public String getMessage() {
		return message;
	}


}
