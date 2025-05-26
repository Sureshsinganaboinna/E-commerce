package com.amazon.backend.authutility;

import java.util.Random;

public class OtpGeneratService {
	
	public static int generatedOtp() {
		
		Random random = new Random();
		
	return	random.nextInt(999999);
	}

}
