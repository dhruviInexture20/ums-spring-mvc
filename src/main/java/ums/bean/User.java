package ums.bean;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;


@Entity
@Table(name = "userData")
public class User {

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int userid;
	
	@Expose
	private String firstname;
	@Expose
	private String lastname;
	
	@Expose
	@Column( unique = true)
	private String email;
	
	@Expose
	private String phone;
	@Expose
	private String password;
	@Expose
	private String designation;
	@Expose
	private String birthdate;
	@Expose
	private String gender;
	
	@Expose
	private String role = "user";
	@Expose
	private String otp;
	
	
	@Lob
	private String profilepic;
	
	@Expose
	private String security_question;
	@Expose
	private String security_answer;
	
	@OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_userid")
	private List<Address> addressList;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

	public String getSecurity_question() {
		return security_question;
	}

	public void setSecurity_question(String security_question) {
		this.security_question = security_question;
	}

	public String getSecurity_answer() {
		return security_answer;
	}

	public void setSecurity_answer(String security_answer) {
		this.security_answer = security_answer;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", phone=" + phone + ", password=" + password + ", designation=" + designation + ", birthdate="
				+ birthdate + ", gender=" + gender + ", role=" + role + ", security_question=" + security_question
				+ ", security_answer=" + security_answer + ", addressList=" + addressList + "]";
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
}
