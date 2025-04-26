package com.jackp.model;

import java.math.BigDecimal;

public class SavingsFundModel {
	private int id;
	private String name;
	private BigDecimal goal;
	private BigDecimal amount;
	
	public SavingsFundModel(String name, BigDecimal goal, BigDecimal amount) {
		this.name = name;
		this.goal = goal;
		this.amount = amount;
	}
	public SavingsFundModel(int id, String name, BigDecimal goal, BigDecimal amount) {
		this.id = id;
		this.name = name;
		this.goal = goal;
		this.amount = amount;
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
	
	
}
