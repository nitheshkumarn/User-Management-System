package com.jsp.usm.User_Management.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class UserResponse {
private int userId;
private String userName;
private String userEmail;

}
