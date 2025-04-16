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

@RequestMapping
@Controller
public class HomeController {
	
	private final UserBusinessInterface userBusinessInterface;
	
	public HomeController(UserBusinessInterface userBusinessInterface) {
		this.userBusinessInterface = userBusinessInterface;
	}

	@GetMapping("/")
	public String viewHomePage(Authentication auth, HttpSession session, Model model) {
		
		String sessionUsername = auth.getName();
		int sessionUserId = userBusinessInterface.getUserIdByUsername(sessionUsername);
		
		session.setAttribute("sessionUserId", sessionUserId);
		
		return "home";

	}
	
	@PostMapping("/filteredDate")
    public String FilteredAttributesByDate(Model model, HttpSession session, @RequestParam(value = "filterByDate", required = false) String selectedDate) {
        if (selectedDate != null && !selectedDate.isEmpty()) {
        	selectedDate = selectedDate + "-01";
        	LocalDate localDate = LocalDate.parse(selectedDate);
            session.setAttribute("filterByDate", localDate);
        }
        return "redirect:/home"; 
    }
	
}
