package com.jackp.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("expenses")
public class ExpenseEntity {

	@Id
	private int id;
	
	@Column("description")
	private String description;
	
	@Column("amount")
	private double amount;
	
	@Column("category")
	private String category;
	
	@Column("date")
	private Date date;
	
	@Column("notes")
	private String notes;
	
	public ExpenseEntity() {}
	
	public ExpenseEntity(int id, String description, double amount, String category, Date date, String notes) {
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.category = category;
		this.date = date;
		this.notes = notes;
	}
	
	public ExpenseEntity(String description, double amount, String category, Date date, String notes) {
		this.description = description;
		this.amount = amount;
		this.category = category;
		this.date = date;
		this.notes = notes;
	}

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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
}
