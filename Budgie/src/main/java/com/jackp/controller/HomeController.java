package com.jackp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jackp.business.ExpenseBusinessInterface;
import com.jackp.business.HomeBusinessInterface;
import com.jackp.business.IncomeBusinessInterface;
import com.jackp.business.UserBusinessInterface;
import com.jackp.model.UserModel;

import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class HomeController {
	
	private final UserBusinessInterface userBusinessInterface;
	private final HomeBusinessInterface homeBusinessInterface;
	private final IncomeBusinessInterface incomeBusinessInterface;
	private final ExpenseBusinessInterface expenseBusinessInterface;
	
	public HomeController(UserBusinessInterface userBusinessInterface, HomeBusinessInterface homeBusinessInterface, IncomeBusinessInterface incomeBusinessInterface, ExpenseBusinessInterface expenseBusinessInterface) {
		this.userBusinessInterface = userBusinessInterface;
		this.homeBusinessInterface = homeBusinessInterface;
		this.incomeBusinessInterface = incomeBusinessInterface;
		this.expenseBusinessInterface = expenseBusinessInterface;
	}

	@GetMapping("/home")
	public String viewHomePage(Authentication auth, HttpSession session, Model model) {
		
		String sessionUsername = auth.getName();
		UserModel user = userBusinessInterface.getUserByUsername(sessionUsername);
		int sessionUserId = user.getId();
		String sessionUserFirstname = user.getFirstName();
		
		session.setAttribute("sessionUserId", sessionUserId);
		session.setAttribute("sessionUsername", sessionUsername);
		session.setAttribute("sessionUserFirstname", sessionUserFirstname);
		
		String spelledOutDate = (String)session.getAttribute("spelledOutDate");
		String selectedDate = (String)session.getAttribute("selectedDate");
		if (selectedDate == null || selectedDate.isEmpty() || selectedDate == "") {
			selectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
	        spelledOutDate = homeBusinessInterface.writtenDateFormatter(selectedDate);
	        session.setAttribute("spelledOutDate", spelledOutDate);
			session.setAttribute("selectedDate", selectedDate);
		}
		
		BigDecimal selectedDateIncome = incomeBusinessInterface.getIncomeSumByDate(sessionUserId, selectedDate);
		BigDecimal selectedDateExpenses = expenseBusinessInterface.getExpenseSumByDate(sessionUserId, selectedDate);
		
		model.addAttribute("selectedDateExpenses", selectedDateExpenses);
		model.addAttribute("selectedDateIncome", selectedDateIncome);
		model.addAttribute("spelledOutDate", spelledOutDate);
		model.addAttribute("selectedDate", selectedDate);
		model.addAttribute("sessionUserFirstname", sessionUserFirstname);
		
		return "home";

	}
	
	@PostMapping("/home/filteredDate")
    public String FilteredAttributesByDate(Model model, HttpSession session, @RequestParam(value = "filterByDate", required = false) String selectedDate) {
        if (selectedDate != null && !selectedDate.isEmpty()) {
            session.setAttribute("selectedDate", selectedDate);
        }
        String spelledOutDate = homeBusinessInterface.writtenDateFormatter(selectedDate);
        session.setAttribute("spelledOutDate", spelledOutDate);
        session.setAttribute("selectedDate", selectedDate);
        

        return "redirect:/home"; 
    }
	
}
