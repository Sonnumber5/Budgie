package com.jackp.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jackp.entity.ExpenseEntity;
import com.jackp.model.ExpenseModel;
import com.jackp.repository.ExpenseRepository;

@Service
public class ExpenseBusinessService implements ExpenseBusinessInterface {
	private final ExpenseRepository expenseRepository;
	
	public ExpenseBusinessService(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}
	
	@Override
	public List<ExpenseModel> getAllByKeyWordDesc(int userId, String keyword) {
		List<ExpenseEntity> searchResults = expenseRepository.findBySearch(keyword, userId);
		List<ExpenseModel> result = new ArrayList<>();
		for (ExpenseEntity entity : searchResults) {
			result.add(entityToModelMapper(entity));
		}
		return result;
	}

	@Override
	public List<List<ExpenseModel>> getAllByDateDesc(int userId, String date) {
		List<ExpenseEntity> allExpenses = expenseRepository.findByDateDescOrder(userId, date);
		HashMap<String, ArrayList<ExpenseModel>> map = new HashMap<>();
		
		for (ExpenseEntity entity : allExpenses) {
			if (!map.containsKey(entity.getCategory())) {
				map.put(entity.getCategory(), new ArrayList<>());
			}
			map.get(entity.getCategory()).add(entityToModelMapper(entity));
		}
		
		List<List<ExpenseModel>> result = new ArrayList<>();
		for (List<ExpenseModel> category : map.values()) {
			result.add(category);
		}
		
		return result;
	}

	@Override
	public BigDecimal getExpenseSumByDate(int userId, String date) {
		return expenseRepository.findTotalByDate(date, userId);
	}

	@Override
	public String addExpense(ExpenseModel model) {
		ExpenseEntity entity = modelToEntityMapper(model);
		if (entity.getCategory() == null) {
			entity.setCategory("Default");
		}
		try {
			expenseRepository.insert(entity);
			return "Successfully added Expense";
		}catch(Exception e) {
			e.printStackTrace();
			return "Failed to insert expense";
		}
	}

	@Override
	public String deleteSelectedExpense(int expenseId) {
		try {
			expenseRepository.deleteById(expenseId);
			return "Succesfully deleted expense";
		}catch(Exception e) {
			e.printStackTrace();
			return "Failed to delete expense";
		}
	}

	@Override
	public ExpenseModel getExpenseById(int expenseId) {
		ExpenseEntity entity = expenseRepository.findById(expenseId);
		return entityToModelMapper(entity);
	}

	@Override
	public String updateSelectedExpense(ExpenseModel expense) {
		ExpenseEntity expenseEntity = new ExpenseEntity(expense.getId(), expense.getDescription(), expense.getAmount(), expense.getCategory(), expense.getDate(), expense.getNotes(), expense.getUserId());
		try {
			expenseRepository.update(expenseEntity);
			return "Successfully updated expense";
		}catch(Exception e) {
			e.printStackTrace();
			return "Error updating expense";
		}
		
	}

	@Override
	public ExpenseModel entityToModelMapper(ExpenseEntity entity) {
		return new ExpenseModel(entity.getId(), entity.getDescription(), entity.getAmount(), entity.getCategory(), entity.getDate(), entity.getNotes(), entity.getUserId());
	}

	@Override
	public ExpenseEntity modelToEntityMapper(ExpenseModel model) {
		return new ExpenseEntity(model.getId(), model.getDescription(), model.getAmount(), model.getCategory(), model.getDate(), model.getNotes(), model.getUserId());
	}

	@Override
	public List<String> getCategoryNames(int sessionUserId) {
		return expenseRepository.findAllCategoryNames(sessionUserId);
	}

}
