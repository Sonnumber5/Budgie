package com.jackp.business;

import java.math.BigDecimal;
import java.util.List;

import com.jackp.entity.ExpenseEntity;
import com.jackp.model.ExpenseModel;


public interface ExpenseBusinessInterface {
	public List<ExpenseModel> getAllByKeyWordDesc(int userId, String keyword);
	public List<List<ExpenseModel>> getAllByDateDesc(int userId, String date);
	public BigDecimal getExpenseSumByDate(int userId, String date);
	public String addExpense(ExpenseModel expense);
	public String deleteSelectedExpense(int expenseId);
	public ExpenseModel getExpenseById(int expenseId);
	public String updateSelectedExpense(ExpenseModel expense);
	public ExpenseModel entityToModelMapper(ExpenseEntity entity);
	public ExpenseEntity modelToEntityMapper(ExpenseModel model);
	public List<String> getCategoryNames(int sessionUserId);
}
