package com.jsp.ums.requestdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	
//	@NotNull(message = "user name should not be null")
//	@NotBlank(message = "User name should not be blank")
	private String userName;
	
//	@NotNull(message = "Email should not be null")
//	@NotBlank(message = "Email should notbe blank")
//	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String userEmail;
	

//	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
//	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
//	message = "Password must contain at least "
//			+ "one uppercase letter"
//			+ "one lowercase letter"
//			+ "one number"
//			+ "one special character")
	private String userPassword;
	
}
