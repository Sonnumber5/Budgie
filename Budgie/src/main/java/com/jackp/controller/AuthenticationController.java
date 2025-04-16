package com.jackp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jackp.business.UserBusinessInterface;
import com.jackp.model.UserModel;
import com.jackp.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthenticationController {
	private final UserRepository userRepository;
	private final UserBusinessInterface userBusinessInterface;
	
	public AuthenticationController(UserRepository userRepository, UserBusinessInterface userBusinessInterface) {
		this.userRepository = userRepository;
		this.userBusinessInterface = userBusinessInterface;
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		return "login";
	}
	
	@GetMapping("/")
	public String redirectToLogin() {
		return "redirect:/login";
	}
	
	@GetMapping("/register")
	public String viewRegisterPage(){
		return "register";
	}
	
	@PostMapping("/register/submitRegistration")
	public String submitRegistration(@RequestParam("firstName") String firstName,
									 @RequestParam("lastName") String lastName, 
									 @RequestParam("username") String username, 
									 @RequestParam("password") String password,
									 @RequestParam("email") String email,
									 Model model) {
		
		UserModel user = new UserModel(firstName, lastName, username, password, email);
		
		String result = userBusinessInterface.addUser(user);
		model.addAttribute("registerMessage", result);
		System.out.println("Form submitted");
		
		return "login";
	}
}
