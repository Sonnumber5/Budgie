package com.jackp.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jackp.business.IncomeBusinessInterface;
import com.jackp.model.IncomeModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class IncomeController {
	
	private final IncomeBusinessInterface incomeBusinessInterface;
	
	public IncomeController(IncomeBusinessInterface incomeBusinessInterface) {
		this.incomeBusinessInterface = incomeBusinessInterface;
	}

	@GetMapping("/income/getIncome")
	public String viewHomePage(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		//int sessionUserId = (Integer) session.getAttribute("sessionUserId");
		//LocalDate selectedDate = (LocalDate)session.getAttribute("selectedDate");
		
		int sessionUserId = 1;
		LocalDate selectedDate = LocalDate.parse("2025-04-01");
		
		List<IncomeModel> incomes = incomeBusinessInterface.getAllByDateDesc(sessionUserId, selectedDate);
		for (IncomeModel income : incomes) {
			System.out.println(income.getDescription());
		}
		return "incomes";
	}
	
}
