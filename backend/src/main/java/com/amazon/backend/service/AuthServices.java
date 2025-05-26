package com.amazon.backend.service;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazon.backend.GlobalException.InvalidPasswordException;
import com.amazon.backend.GlobalException.UserAlreadyExistss;
import com.amazon.backend.GlobalException.UserNotFoundException;
import com.amazon.backend.authutility.OtpGeneratService;
import com.amazon.backend.constant.AuthConstants;
import com.amazon.backend.entities.SignupEntity;
import com.amazon.backend.enums.UserRole;
import com.amazon.backend.pojo.ForgotPassword;
import com.amazon.backend.pojo.Login;
import com.amazon.backend.pojo.ResetPassword;
import com.amazon.backend.pojo.SignupData;
import com.amazon.backend.repository.SignupRespository;

@Service
public class AuthServices {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired 
	private SignupRespository signupRespository;
	
	@Autowired
	private EmailService emailService;
	
	public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public Map<String, Object> userSignup(SignupData signupData)
	{
		Optional<SignupEntity> dbdataOptional = signupRespository.findByemail(signupData.getEmail());
		
		if(dbdataOptional.isPresent())
		{
			throw new UserAlreadyExistss(AuthConstants.USER_ALREADY_EXISTS +signupData.getEmail()+AuthConstants.SIGNNUP_ANOTHER_EMAIL);
		}
		 
		SignupEntity signupEntity = new SignupEntity();
		signupEntity.setFirstName(signupData.getFirstName());
		signupEntity.setLastName(signupData.getLastName());
		signupEntity.setEmail(signupData.getEmail());
		signupEntity.setPasswordHash(passwordEncoder.encode(signupData.getPassword()));
		signupEntity.setPhoneNumber(signupData.getPhoneNumber());
		if((signupData.getRole()!=null))
		{
			signupEntity.setRole(signupData.getRole());
		}
		else {
			signupEntity.setRole(UserRole.BUYER);
		}
		
	SignupEntity signupEntity2	=  signupRespository.save(signupEntity);
	
	    String jwttoken = jwtService.generateJwtToken(signupEntity2);
	    
	    Map<String, Object> resMap = new HashMap<String, Object>();
	    resMap.put("UserInfo", signupEntity2);
	    resMap.put("jwttokenn", jwttoken);
		
		return resMap;
		}
	
	public Map<String, Object> userLoginService(Login login)
	{

		Optional<SignupEntity> userOptional = signupRespository.findByemail(login.getEmail());
		if (userOptional.isEmpty()) {
			throw new UserNotFoundException(AuthConstants.USER_NOT_FOUND_EXECEPTION);
		}
		SignupEntity signupEntity = userOptional.get();
		if (passwordEncoder.matches(login.getPassword(), signupEntity.getPasswordHash()) == false) {
			throw new InvalidPasswordException(AuthConstants.INVALID_PASSWORD);
		}

		String token = jwtService.generateJwtToken(signupEntity);

		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("UserInfo", signupEntity);
		resMap.put("jwttokenn", token);

		return resMap;
	}
	
	public void forgotPasswordGenerateOtp(ForgotPassword forgotPassword) {

		Optional<SignupEntity> userOptional = signupRespository.findByemail(forgotPassword.getEmail());
		if (userOptional.isEmpty()) {
			throw new UserNotFoundException(AuthConstants.USER_NOT_FOUND_EXECEPTION);
		}
		SignupEntity user = userOptional.get();
		
		int otp = OtpGeneratService.generatedOtp();
		System.out.println(otp);
		
		user.setOtp(otp);
		signupRespository.save(user);
		String emailbody = "Hi" + user.getFirstName() + "," + "Please use below OTP to reset your password"
				+ "OTP is :- " + otp + "Thank you";
		emailService.sendMail("OTP for reset password", emailbody, "rangulu1998@gmail.com", user.getEmail());
		
		
	}
	
	public void resetPasswordd(ResetPassword resetPassword) {
		Optional<SignupEntity> dbinfo = signupRespository.findByemail(resetPassword.getEmail());

		if (dbinfo.isEmpty()) {

			throw new UserNotFoundException(AuthConstants.USER_NOT_FOUND_EXECEPTION);
		}
		SignupEntity usedata = dbinfo.get();

		if (usedata.getOtp() != resetPassword.getOtp()) {

			throw new UserNotFoundException(AuthConstants.ENTER_VALID_OTP);

		}
		if (!resetPassword.getConfirmedPassword().equals(resetPassword.getNewPassword())) {

			throw new UserNotFoundException(AuthConstants.NEW_AND_CONFIRM_PASSWORD_MUST_BE_SAME);

		}
		usedata.setPasswordHash(passwordEncoder.encode(resetPassword.getConfirmedPassword()));

		signupRespository.save(usedata);

	}
	
	
	
	
}
