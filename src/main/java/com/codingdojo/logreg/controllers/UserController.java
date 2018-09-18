package com.codingdojo.logreg.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.logreg.models.User;

import com.codingdojo.logreg.services.UserService;

@Controller

public class UserController {
	private final UserService uS;
//	private final UserRepository uR;
	
	public UserController(UserService uS) {
		this.uS=uS;
//		this.uR=uR;, UserRepository uR
	}
	@GetMapping("/users")
	public String showRegister(@ModelAttribute("user") User user, HttpSession session) {
		session.invalidate();
		return "register.jsp";
	}
	@PostMapping("/users")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "register.jsp";
	} else {
		if(!user.getPassword().equals(user.getConfirm())) {
			model.addAttribute("userError", "password not match");
			return "register.jsp";
		} else {
			String pw= BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(pw);
			User exists = uS.findByEmail(user.getEmail());
			
			if (exists != null) {
				model.addAttribute("userError", "user already exist");
				return "register.jsp";
			}else {
				User u=uS.create(user);
				session.setAttribute("user", u.getId());
				return "redirect:/users/dashboard";
			}
		}
	}
	}
	@PostMapping("/users/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		User u = uS.findByEmail(email);
		if (u == null) {
			model.addAttribute("loginError", "The email doesnt exist");
			model.addAttribute("user",new User());
			return "register.jsp";
		} else {
			boolean matches=BCrypt.checkpw(password, u.getPassword());
			if (matches) {
				session.setAttribute("user", u.getId());
				return "redirect:/users/dashboard";
			} else {
				model.addAttribute("loginError", "Password does not match");
				model.addAttribute("user",new User());
				return "register.jsp";
			}
		}
		
	}
	@GetMapping("/users/dashboard")
	
	public String dashboard(Model model, HttpSession session) {
		System.out.println("in dashboard");
		Long id = (Long)session.getAttribute("user");
		if(id == null) {
			return "redirect:/users";
		}else {
			User u = uS.findById(id);
			model.addAttribute("user", u);
			return "dashboard.jsp";
		}
	}
}
