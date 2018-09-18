package com.codingdojo.logreg.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.codingdojo.logreg.models.User;
import com.codingdojo.logreg.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository uR;
	public UserService(UserRepository uR) {
		this.uR= uR;
	}
//	public void create(User user) {
//		uR.save(user);
//	}
	public User create(User user) {
		return uR.save(user);
	}
	public ArrayList<User> findAll(){
//		this line is casting because in repository we created list not arraylist
		return (ArrayList<User>) uR.findAll();
	}
	public User findById(Long id) {
		return uR.findById(id).get();
	}
	public User findByEmail(String email) {
		return uR.findByEmail(email);
	}
	public User findByEmailAndPassword(String email, String password) {
		return uR.findByEmailAndPassword(email, password);
	}
	public User update(User user) {
		return uR.save(user);
	}
	public void destroy(Long id) {
		uR.deleteById(id);
	}
	
	
}
