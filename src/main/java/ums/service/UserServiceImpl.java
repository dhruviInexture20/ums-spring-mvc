package ums.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ums.bean.EmailMessageBean;
import ums.bean.User;
import ums.dao.GenericDao;
import ums.utility.DataUtility;
import ums.utility.EmailUtility;
import ums.utility.PasswordSecurity;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private GenericDao<User> dao;

	@Override
	public int create(User user) throws Exception {
		
		PasswordSecurity ps = new PasswordSecurity();
		String password = user.getPassword();
		
		user.setPassword(ps.encrypt(password)); // encrypt
		
		return dao.create(user);
	}

	@Override
	public String userExist(String email) {
		// TODO Auto-generated method stub

		if (dao.isEmailExist(email)) {
			return "false";
		} else
			return "true";

	}

	@Override
	public User getUser(String email, String password) throws Exception {

		PasswordSecurity ps = new PasswordSecurity();
		String pass = ps.encrypt(password); // encrypt
		User user = dao.find(email, pass);
		
		if(user != null) {
			user.setPassword(password);	
		}
		
		return user;

	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return dao.getAllUsers();
	}

	@Override
	public void deleteUser(int userid) {

		User user = dao.findById(userid);
		dao.delete(user);

	}

	@Override
	public User getUserById(String userid) throws Exception {
		PasswordSecurity ps = new PasswordSecurity();
		User user = dao.findById(Integer.parseInt(userid));
		
		String password = user.getPassword();

		user.setPassword(ps.decrypt(password));
		return user;
	}

	@Override
	public String authenticateUserForForgetPass(String email, String s_que, String s_ans) {

		User user = dao.getUserByEmail(email);
		String msg;
		if (user == null) {
			msg = "Email is not registered";
		} else if (user.getSecurity_answer().equals(s_ans) && user.getSecurity_question().equals(s_que)) {
			System.out.println("hellooooo");
			msg = null;
		} else {
			msg = "Wrong security question or answer";
		}

		return msg;
	}

	@Override
	public String sendOTPMail(String email) throws MessagingException {

		String otp = DataUtility.generateOTP();

		EmailMessageBean emailbean = new EmailMessageBean();

		emailbean.setTo(email);
		emailbean.setSubject("OTP For Rsetpassword");
		emailbean.setMessage("Your One Time Password is " + otp);

		EmailUtility emailUtility = new EmailUtility();
		emailUtility.sendMail(emailbean);
		saveOTP(email, otp);

		return otp;
	}

	public void saveOTP(String email, String otp) {
		// TODO Auto-generated method stub
		
		User user = dao.getUserByEmail(email);
		user.setOtp(otp);
		
		dao.update(user);
		
	}

	@Override
	public void updateUser(User user) throws Exception {
		
		PasswordSecurity ps = new PasswordSecurity();
		String password = user.getPassword();
		
		user.setPassword(ps.encrypt(password)); // encrypt
		
		
		dao.update(user);
	}

	@Override
	public boolean verifyOtp(String email, String otp) {
		
		User user = dao.getUserByEmail(email);
		
		if(user.getOtp().equals(otp)) {
			return true;
		}
		
		return false;
	}

	@Override
	public void updatePssword(String email, String password) throws Exception {
		
		PasswordSecurity ps = new PasswordSecurity();
		password = ps.encrypt(password);
		
		User user = dao.getUserByEmail(email);
		user.setPassword(password);
		
		dao.update(user);
	}


}
