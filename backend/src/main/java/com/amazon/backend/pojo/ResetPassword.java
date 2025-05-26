package com.amazon.backend.pojo;

import com.amazon.backend.constant.AuthConstants;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ResetPassword {
	
	@NotNull(message = AuthConstants.ERROR_EMAIL_REQUIRED)
    @Pattern(regexp = "^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,})$", message = AuthConstants.ERROR_EMAIL_NOT_VALID)
	private String email;
	
	@NotNull(message = AuthConstants.ERROR_FIRST_NAME_REQUIRED)
	private int otp;
	
	
	@NotNull(message = AuthConstants.ERROR_PASSWORD_REUIRED)
	@Size(min = 4, max = 6, message= AuthConstants.ERROR_PASSWORD_4_TO_6)
	private String newPassword;
	
	
	@NotNull(message = AuthConstants.ERROR_PASSWORD_REUIRED)
	@Size(min = 4, max = 6, message= AuthConstants.ERROR_PASSWORD_4_TO_6)
	private String confirmedPassword;
	

}
