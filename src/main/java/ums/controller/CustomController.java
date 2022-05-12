package ums.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.search.AddressStringTerm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ums.bean.Address;
import ums.bean.User;
import ums.service.UserService;

@Controller
public class CustomController {

	Logger logger = Logger.getLogger(CustomController.class);

	@Autowired
	private UserService service;

	@RequestMapping(path = { "/", "/login" })
	public String home() {
		return "login";
	}

	@RequestMapping("/registration")
	public String registration() {
		
		return "registration";
		
	}

	@RequestMapping("/adminDashboard")
	public String goToDashboard() {
		return "adminDashboard";
	}

	@RequestMapping("/profile")
	public String goToProfile() {
		return "profile";
	}

	@RequestMapping("/forgetpassword")
	public String goForgetPassPage() {
		return "forgetpassword";
	}

	@RequestMapping("/fillOtp")
	public String showFillOtp() {
		return "fillOtp";
	}

	@PostMapping(path = "/process_form", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String register(@ModelAttribute User user, @RequestParam("profilepicture") MultipartFile filepart,
			Model model) {

		BasicConfigurator.configure();

		try {
			String base64Image = Base64.getEncoder().encodeToString(filepart.getBytes());

			user.setProfilepic(base64Image);

		} catch (IOException e) {
			logger.error(e);
		}

		int id = service.create(user);

		model.addAttribute("success_msg", "User registered successfully");
		return "login";
	}

	@ResponseBody
	@RequestMapping("/CheckEmailAvailability")
	public String checkEmail(@RequestParam String email) {

		String isExist = service.userExist(email);

		return isExist;
	}

	@RequestMapping("/process_login")
	public String checkLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, Model model) {
		BasicConfigurator.configure();
		User user = service.getUser(email, password);

		if (user == null) {

			model.addAttribute("error_msg", "Wrong Email or Password");

			return "login";
		} else if (user.getRole().equals("user")) {
			logger.info("user login");
			session.setAttribute("role", "user");
			session.setAttribute("user", user);

			return "redirect:profile";
		} else {
			logger.info("admin login");
			session.setAttribute("role", "admin");

			return "redirect:adminDashboard";
		}
	}

	@ResponseBody
	@RequestMapping(path = "/GetAllUsers", produces = "application/json")
	public JsonObject getAllUsers() {

		List<User> users = service.getUserList();

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

		JsonObject json = new JsonObject();
		json.add("data", gson.toJsonTree(users));

		return json;

	}

	@ResponseBody
	@RequestMapping(path = "/DeleteUserController")
	public String deleteuser(@RequestParam("userid") int userid) {

		service.deleteUser(userid);

		return "true";
	}

	@PostMapping(path = "/EditUserController")
	public String editUser(@RequestParam("userid") String userid, HttpSession session) {

		session.setAttribute("userid", userid);

		User user = service.getUserById(userid);

		session.setAttribute("user", user);

		return "redirect:registration";
	}

	@RequestMapping("/LogOutController")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:login";

	}

	@ResponseBody
	@PostMapping(path = "/GetUserAddress", produces = "application/json")
	public JsonElement getUserAddress(@RequestParam("userid") String userid) {

		List<Address> address = service.getUserById(userid).getAddressList();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJsonTree(address);

	}

	@PostMapping(path = "/process_edits", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String editUserData(@ModelAttribute User editedUser, HttpSession session,
			@RequestParam("profilepicture") MultipartFile filepart) {
		User user = (User) session.getAttribute("user");

		editedUser.setEmail(user.getEmail());

		String base64Image = null;

		if (filepart.getSize() > 0) {
			try {
				base64Image = Base64.getEncoder().encodeToString(filepart.getBytes());
			} catch (IOException e) {
				logger.error(e);
			}
		} else {
			base64Image = user.getProfilepic();
		}

		editedUser.setProfilepic(base64Image);

		editedUser.getAddressList().stream().forEach(address -> {

			address.setUser(editedUser);

		});

		service.updateUser(editedUser);

		String role = (String) session.getAttribute("role");

		if (role.equals("admin")) {
			return "redirect:adminDashboard";
		} else {
			session.setAttribute("user", editedUser);
			return "redirect:profile";
		}

	}

	@PostMapping("/ForgetPasswordController")
	public String processForgetPassword(@RequestParam("email") String email,
			@RequestParam("security_question") String s_que, @RequestParam("security_answer") String s_ans,
			Model model) {

		BasicConfigurator.configure();
		String msg = service.authenticateUserForForgetPass(email, s_que, s_ans);

		if (msg == null) {
			model.addAttribute("email", email);
			String otp = null;
			try {
				otp = service.sendOTPMail(email);
			} catch (MessagingException e) {
				logger.error(e);
			}

			service.saveOTP(email, otp);

			return "fillOtp";
		} else {
			model.addAttribute("error_msg", msg);
			return "forgetpassword";
		}
	}

	@PostMapping("/VerifyOTPController")
	public String checkOtp(@RequestParam("email") String email, @RequestParam("otp") String otp, Model model) {

		// verify otp

		model.addAttribute("email", email);
		boolean isCorrect = service.verifyOtp(email, otp);

		if (isCorrect) {
			return "resetpassword";

		} else {
			model.addAttribute("error_msg", "You have entered Wrong OTP");
			return "fillOtp";
		}
	}

	@PostMapping("/UpdatePasswordController")
	public String updatePassword(@RequestParam("password") String password, @RequestParam("email") String email,
			Model model) {

		service.updatePssword(email, password);

		model.addAttribute("success_msg", "Password changed successfully");

		return "login";

	}
}
