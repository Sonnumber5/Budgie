package com.jackp.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("savingsFunds")
public class SavingsFundEntity {
	@Id
	private int id;
	@Column("name")
	private String name;
	@Column("goal")
	private BigDecimal goal;
	@Column("amount")
	private BigDecimal amount;
	@Column("userId")
	private int userId;
	
	public SavingsFundEntity() {}
	
	public SavingsFundEntity(String name, BigDecimal goal, BigDecimal amount, int userId) {
		this.name = name;
		this.goal = goal;
		this.amount = amount;
		this.userId = userId;
	}
	public SavingsFundEntity(int id, String name, BigDecimal goal, BigDecimal amount, int userId) {
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
