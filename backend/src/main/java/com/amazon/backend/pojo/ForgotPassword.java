package com.amazon.backend.pojo;

import com.amazon.backend.constant.AuthConstants;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ForgotPassword {
	
	@NotNull(message = AuthConstants.ERROR_EMAIL_REQUIRED)
	@Pattern(regexp = "^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,})$", message = AuthConstants.ERROR_EMAIL_NOT_VALID)
	private String email;

}
