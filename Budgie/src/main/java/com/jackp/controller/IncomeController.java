package com.jackp.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String viewHomePage(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		int sessionUserId = (Integer) session.getAttribute("sessionUserId");
		//String selectedDate = (String)session.getAttribute("selectedDate");
		String selectedDate = "2025-04";
		
		List<IncomeModel> incomes = incomeBusinessInterface.getAllByDateDesc(sessionUserId, selectedDate);
		model.addAttribute("incomes", incomes);
		model.addAttribute("sessionUserId", sessionUserId);
		
		return "income";
	}
	
	@PostMapping("/income/addIncome")
	public String addIncome(HttpSession session, @ModelAttribute IncomeModel income) {
		
		int sessionUserId = (Integer)session.getAttribute("sessionUserId");
		income.setUserId(sessionUserId);
		String addIncomeMessage = incomeBusinessInterface.addIncome(income);
		
		return "redirect:/income/getIncome";
		
	}
	
	@PostMapping("/income/deleteIncome")
	public String deleteIncome(Model model, @RequestParam int incomeId) {

		String deleteIncomeMessage = incomeBusinessInterface.deleteSelectedIncome(incomeId);
		
		return "redirect:/income/getIncome";
	}
	
	@PostMapping("/income/incomeToUpdate")
	public String updateSelectedIncome(Model model, @RequestParam int incomeId) {

		IncomeModel income = incomeBusinessInterface.getIncomeById(incomeId);
		//System.out.println(income.getId()+ "\n" + income.getDescription() + "\n" + income.getAmount()+ "\n" + income.getDate() + "\n" + income.getNotes() + "\n" + income.getUserId());

		model.addAttribute("income", income);
		return "updateIncome";
	}
	
	@PostMapping("/income/incomeToUpdate/confirmUpdate")
	public String updateIncome(Model model, @ModelAttribute IncomeModel income, @RequestParam int incomeId) {
		income.setId(incomeId);
		System.out.println(income.getId() + "\n" + income.getDescription() + "\n" + income.getAmount()+ "\n" + income.getDate() + "\n" + income.getNotes() + "\n" + income.getUserId());
		String updateIncomeMessage = incomeBusinessInterface.updateSelectedIncome(income);
		System.out.println(updateIncomeMessage);
		return "redirect:/income/getIncome";
	}
}
