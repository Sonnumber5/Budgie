package com.jackp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jackp.business.UserBusinessInterface;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class HomeController {
	
	private final UserBusinessInterface userBusinessInterface;
	
	public HomeController(UserBusinessInterface userBusinessInterface) {
		this.userBusinessInterface = userBusinessInterface;
	}

	@GetMapping("/home")
	public String viewHomePage(Authentication auth, HttpSession session, Model model) {
		
		String sessionUsername = auth.getName();
		int sessionUserId = userBusinessInterface.getUserIdByUsername(sessionUsername);
		
		session.setAttribute("sessionUserId", sessionUserId);
		
		return "home";

	}
	
}
