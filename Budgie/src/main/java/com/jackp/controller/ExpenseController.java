package com.jackp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jackp.business.ExpenseBusinessInterface;
import com.jackp.model.ExpenseModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ExpenseController {
	
	private final ExpenseBusinessInterface expenseBusinessInterface;
	
	public ExpenseController(ExpenseBusinessInterface expenseBusinessInterface) {
		this.expenseBusinessInterface = expenseBusinessInterface;
	}

	@GetMapping("/expenses/getExpenses")
	public String viewExpensesPage(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		int sessionUserId = (Integer) session.getAttribute("sessionUserId");
		String selectedDate = (String)session.getAttribute("selectedDate");
		String spelledOutDate = (String)session.getAttribute("spelledOutDate");
		
		List<List<ExpenseModel>> categories = expenseBusinessInterface.getAllByDateDesc(sessionUserId, selectedDate);
		model.addAttribute("categories", categories);
		model.addAttribute("sessionUserId", sessionUserId);
		model.addAttribute("spelledOutDate", spelledOutDate);
		
		return "expenses";
	}
	
	@PostMapping("/expenses/addExpense")
	public String addExpense(HttpSession session, @ModelAttribute ExpenseModel expense, @RequestParam(required = false) String newCategory) {
		
		if (newCategory != null && !newCategory.isEmpty()) {
			expense.setCategory(newCategory);
		}
		if (expense.getCategory() == null) {
			expense.setCategory("Default Category");
		}
		
		int sessionUserId = (Integer)session.getAttribute("sessionUserId");
		expense.setUserId(sessionUserId);
		String addExpenseMessage = expenseBusinessInterface.addExpense(expense);
		
		return "redirect:/expenses/getExpenses";
		
	}
	
	@PostMapping("/expenses/deleteExpense")
	public String deleteExpense(Model model, @RequestParam int expenseId) {

		String deleteExpenseMessage = expenseBusinessInterface.deleteSelectedExpense(expenseId);
		
		return "redirect:/expenses/getExpenses";
	}
	
	@PostMapping("/expenses/expenseToUpdate")
	public String updateSelectedExpense(HttpSession session, Model model, @RequestParam int expenseId) {

		int sessionUserId = (Integer)session.getAttribute("sessionUserId");
		
		ExpenseModel expenseToUpdate = expenseBusinessInterface.getExpenseById(expenseId);
		List<String> categoryNames = expenseBusinessInterface.getCategoryNames(sessionUserId);
		
		model.addAttribute("categories", categoryNames);
		model.addAttribute("expenseToUpdate", expenseToUpdate);
		return "updateExpense";
	}
	
	@PostMapping("/expenses/expenseToUpdate/confirmUpdate")
	public String updateExpense(Model model, @ModelAttribute ExpenseModel expense, @RequestParam int expenseId, @RequestParam(required = false) String newCategory) {
		
		if (newCategory != null && !newCategory.isEmpty()) {
			expense.setCategory(newCategory);
		}
		if (expense.getCategory() == null) {
			expense.setCategory("Default Category");
		}
		
		expense.setId(expenseId);
		String updateExpenseMessage = expenseBusinessInterface.updateSelectedExpense(expense);
		System.out.println(updateExpenseMessage);
		return "redirect:/expenses/getExpenses";
	}
}
