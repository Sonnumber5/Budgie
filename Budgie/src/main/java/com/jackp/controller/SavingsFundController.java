package com.jackp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SavingsFundController {

	@GetMapping("/savingsFunds/getFunds")
	public String viewSavingsFundsPage() {
		return "savingsFunds";
	}
}
