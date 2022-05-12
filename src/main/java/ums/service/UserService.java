package ums.service;


import java.util.List;

import javax.mail.MessagingException;

import ums.bean.Address;
import ums.bean.User;


public interface UserService {
	
	int create(User user);

//	void authenticateUser(String email, String password);

	String userExist(String email);

	User getUser(String email, String password);

	List<User> getUserList();

	void deleteUser(int userid);

	User getUserById(String userid);

	String authenticateUserForForgetPass(String email, String s_que, String s_ans);

	String sendOTPMail(String email) throws MessagingException;

	void saveOTP(String email, String otp);

	void updateUser(User user);

	boolean verifyOtp(String email, String otp);

	void updatePssword(String email, String password);

	
}
