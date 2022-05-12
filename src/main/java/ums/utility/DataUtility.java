package ums.utility;

import java.util.Random;

public class DataUtility {

	private static final Random RANDOM = new Random();
	
	public static String generateOTP() {
			
		 int otp = RANDOM.nextInt(999999);
		 return String.format("%06d",otp);
	 }
}
