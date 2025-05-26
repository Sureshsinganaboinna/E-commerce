package com.amazon.backend.pojo;

import com.amazon.backend.constant.AuthConstants;
import com.amazon.backend.enums.UserRole;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupData {
	
	@NotNull(message = AuthConstants.ERROR_FIRST_NAME_REQUIRED)
	@Size(min=4, message = AuthConstants.ERROR_FIRST_NAME_ATLEAST_4_CHAR)
	private String firstName;
	
	@NotNull(message = AuthConstants.ERROR_LAST_NAME_REQUIRED)
	@Size(min=4, message = AuthConstants.ERROR_Last_NAME_ATLEAST_4_CHAR)
	private String lastName;
	
	@NotNull(message = AuthConstants.ERROR_EMAIL_REQUIRED)
	@Pattern(regexp = "^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,})$", message = AuthConstants.ERROR_EMAIL_NOT_VALID)
	private String email;
	
	@NotNull(message = AuthConstants.ERROR_PASSWORD_REUIRED)
	@Size(min = 4, max = 6, message= AuthConstants.ERROR_PASSWORD_4_TO_6)
	private String password;
	
	@NotNull(message = AuthConstants.ERROR_PHONENUMBER_REQUIRED)
	@Size(min = 10, message = AuthConstants.ERROR_PHONENUMBER_10_DIGITS)
	private String phoneNumber;
	
	private UserRole role;
	
}
