package com.jackp.model;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;


public class IncomeModel {
	
	private int id;
	
	private String description;
	
	private double amount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	private String notes;
	
	private int userId;
	
	
	public IncomeModel() {}
	
	public IncomeModel(int id, String description, double amount, Date date, String notes, int userId) {
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.date = date;
		this.notes = notes;
		this.userId = userId;
	}
	
	public IncomeModel(String description, double amount, Date date, String notes, int userId) {
		this.description = description;
		this.amount = amount;
		this.date = date;
		this.notes = notes;
		this.userId = userId;
	}

	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
