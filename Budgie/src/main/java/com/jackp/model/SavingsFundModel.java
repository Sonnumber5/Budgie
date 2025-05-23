package com.jackp.model;

import java.math.BigDecimal;

public class SavingsFundModel {
	private int id;
	private String name;
	private BigDecimal goal;
	private BigDecimal amount;
	private int userId;
	
	public SavingsFundModel() {}

	public SavingsFundModel(String name, BigDecimal goal, BigDecimal amount, int userId) {
		this.name = name;
		this.goal = goal;
		this.amount = amount;
		this.userId = userId;
	}
	public SavingsFundModel(int id, String name, BigDecimal goal, BigDecimal amount, int userId) {
		this.id = id;
		this.name = name;
		this.goal = goal;
		this.amount = amount;
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getGoal() {
		return goal;
	}
	public void setGoal(BigDecimal goal) {
		this.goal = goal;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
