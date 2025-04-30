package com.jackp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jackp.business.SavingsFundBusinessInterface;
import com.jackp.model.SavingsFundModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SavingsFundController {
	private final SavingsFundBusinessInterface savingsFundBusinessInterface;
	
	public SavingsFundController(SavingsFundBusinessInterface savingsFundBusinessInterface) {
		this.savingsFundBusinessInterface = savingsFundBusinessInterface;
	}
	
	@GetMapping("/savingsFunds/getFunds")
	public String viewSavingsFundsPage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		int sessionUserId = (Integer)session.getAttribute("sessionUserId");
		List<SavingsFundModel> savingsFunds = savingsFundBusinessInterface.getAllFunds(sessionUserId);
		
		model.addAttribute("savingsFunds", savingsFunds);
		return "savingsFunds";
	}
	
	@GetMapping("/savingsFunds/addFund")
	public String viewAddFundPage(HttpSession session, Model model) {
		int sessionUserId = (Integer)session.getAttribute("sessionUserId");
		model.addAttribute("sessionUserId", sessionUserId);
		
		return "addFund";
	}
	
	@PostMapping("/savingsFunds/addFund/confirm")
	public String addSavingsFund(Model model, @ModelAttribute SavingsFundModel savingsFund) {
		savingsFundBusinessInterface.addSavingsFund(savingsFund);
		return "redirect:/savingsFunds/getFunds";
	}
	
	@PostMapping("/savingsFunds/deleteFund")
	public String deleteSavingsFund(@RequestParam int fundId) {
		String deleteMessage = savingsFundBusinessInterface.deleteSelectedFund(fundId);
		return "redirect:/savingsFunds/getFunds";
	}
	
	@PostMapping("/savingsFunds/fundToUpdate")
	public String updateSelectedFund(Model model, @RequestParam int fundId) {
		SavingsFundModel fundToUpdate = savingsFundBusinessInterface.getSavingsFundById(fundId);
		String header = "Update Fund: " + fundToUpdate.getName();
		
		model.addAttribute("fundToUpdate", fundToUpdate);
		model.addAttribute("header", header);
		return "updateSavingsFund";
	}
	
	@PostMapping("/savingsFunds/fundToUpdate/confirmUpdate")
	public String confirmUpdateSavingsFund(@ModelAttribute SavingsFundModel savingsFundModel, @RequestParam int fundId) {
		savingsFundModel.setId(fundId);
		savingsFundBusinessInterface.updateSelectedSavingsFund(savingsFundModel);

		return "redirect:/savingsFunds/getFunds";
	}
}
