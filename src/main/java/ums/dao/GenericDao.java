package ums.dao;

import java.util.List;

import ums.bean.User;

public interface GenericDao<T> {
	
	int create(T t);
	
	T find(String email, String password);
	
	T update(T t);

	boolean isEmailExist(String email);

	List<T> getAllUsers();

	void delete(T t);

	User findById(int userid);

	T getUserByEmail(String email);


}
