package com.jackp.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("income")
public class IncomeEntity {
	@Id
	private int id;
	
	@Column("description")
	private String description;

	@Column("amount")
	private BigDecimal amount;
	
	@Column("date")
	private Date date;
	
	@Column("notes")
	private String notes;
	
	@Column("userId")
    private int userId;
	
	public IncomeEntity(){}
	
	public IncomeEntity(int id, String description, BigDecimal amount, Date date, String notes, int userId) {
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.date = date;
		this.notes = notes;
		this.userId = userId;
	}
	
	public IncomeEntity(String description, BigDecimal amount, Date date, String notes, int userId) {
		this.description = description;
		this.amount = amount;
		this.date = date;
		this.notes = notes;
		this.userId = userId;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
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
