package com.jackp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jackp.business.UserBusinessInterface;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;

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
		session.setAttribute("sessionUsername", sessionUsername);
		model.addAttribute("sessionUsername", sessionUsername);
		
		return "home";

	}
	
	@PostMapping("/home/filteredDate")
    public String FilteredAttributesByDate(Model model, HttpSession session, @RequestParam(value = "filterByDate", required = false) String selectedDate) {
        if (selectedDate != null && !selectedDate.isEmpty()) {

            session.setAttribute("selectedDate", selectedDate);
        }
        return "redirect:/home"; 
    }
	
}
