package com.jackp.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.format.annotation.DateTimeFormat;

public class ExpenseModel {

	private int id;
	
    private String description;
	
    private BigDecimal amount;
	
    private String category;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
	
    private String notes;
    
	@Column("userId")
	private int userId;

    public ExpenseModel() {}
    
    public ExpenseModel(int id, String description, BigDecimal amount, String category, Date date, String notes, int userId) {
        this.id = id;
    	this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.notes = notes;
        this.userId = userId;
    }
    
    public ExpenseModel(String description, BigDecimal amount, String category, Date date, String notes, int userId) {
        this.description = description;
        this.amount = amount;
        this.category = category;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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
    
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
