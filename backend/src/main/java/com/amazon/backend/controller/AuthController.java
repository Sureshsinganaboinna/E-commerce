package com.amazon.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.constant.AuthConstants;
import com.amazon.backend.entities.SignupEntity;
import com.amazon.backend.pojo.ForgotPassword;
import com.amazon.backend.pojo.Login;
import com.amazon.backend.pojo.ResetPassword;
import com.amazon.backend.pojo.SignupData;
import com.amazon.backend.response.ApiResponse;
import com.amazon.backend.service.AuthServices;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/Auth")
public class AuthController {

	@Autowired
	private AuthServices authServices;

	@PostMapping("/Signup")
	public ResponseEntity<ApiResponse<SignupEntity>> userSignup(@Valid @RequestBody SignupData signupData) {
		Map<String, Object> signupEntity = authServices.userSignup(signupData);
		ApiResponse<SignupEntity> apiiApiResponse = new ApiResponse<>(true, AuthConstants.SCCESS_SIGNUP,
				(SignupEntity) signupEntity.get("UserInfo"));
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Barear " + signupEntity.get("jwttokenn").toString());
		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(apiiApiResponse);
	}

	@GetMapping("/Login")
	public ResponseEntity<ApiResponse<SignupEntity>> userLogin(@RequestBody Login login) {
		Map<String, Object> signupEntity = authServices.userLoginService(login);
		ApiResponse<SignupEntity> apiiApiResponse = new ApiResponse<>(true, AuthConstants.SCCESS_LOGGED,
				(SignupEntity) signupEntity.get("UserInfo"));
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Barear " + signupEntity.get("jwttokenn").toString());
		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(apiiApiResponse);
	}

	@GetMapping("/forgotpassword")
	public ResponseEntity<Object> forgotoPassword(@Valid @RequestBody ForgotPassword forgotPassword) {
		System.out.println(forgotPassword.getEmail());
	authServices.forgotPasswordGenerateOtp(forgotPassword);
	ApiResponse<String> appApiResponse = new ApiResponse<>(true, AuthConstants.OTP_SENT_TO_YOUR_EMAIL, "");
	
	return ResponseEntity.status(HttpStatus.OK).body(appApiResponse);
	}
	
	
	@PostMapping("/resetpassword")
	public ResponseEntity<ApiResponse<String>> postMethodName(@Valid @RequestBody ResetPassword resetPassword) {
		
		authServices.resetPasswordd(resetPassword);
		System.out.println(resetPassword.getEmail());
		System.out.println(resetPassword.getConfirmedPassword());
		
		ApiResponse<String> appApiResponse = new ApiResponse<>(true, AuthConstants.YOUR_PASSWORD_SUCCESSFULLY_CHANGED, "");
		
		return ResponseEntity.status(HttpStatus.OK).body(appApiResponse);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
