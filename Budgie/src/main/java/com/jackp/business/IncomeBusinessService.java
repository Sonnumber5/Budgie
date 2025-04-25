package com.jackp.business;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jackp.entity.IncomeEntity;
import com.jackp.model.IncomeModel;
import com.jackp.repository.IncomeRepository;

@Service
public class IncomeBusinessService implements IncomeBusinessInterface{
	
	private final IncomeRepository incomeRepository;
	
	public IncomeBusinessService(IncomeRepository incomeRepository) {
		this.incomeRepository = incomeRepository;
	}

	@Override
	public List<IncomeModel> getAllByKeyWordDesc(int userId, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IncomeModel> getAllByDateDesc(int userId, String date) {
		List<IncomeEntity> incomes = incomeRepository.findByDateDescOrder(userId, date);
		List<IncomeModel> result = new ArrayList<>();
		
		for (IncomeEntity entity : incomes) {
			IncomeModel model = new IncomeModel(entity.getId(), entity.getDescription(), entity.getAmount(), entity.getDate(), entity.getNotes(), entity.getUserId());
			result.add(model);
		}
		
		return result;
	}

	@Override
	public BigDecimal getIncomeSumByDate(int userId, String date) {
		try {
			return incomeRepository.findSumByDate(userId, date);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String addIncome(IncomeModel income) {
		IncomeEntity entity = new IncomeEntity(income.getDescription(), income.getAmount(), income.getDate(), income.getNotes(), income.getUserId());
		try {
			incomeRepository.insert(entity);
			return "Successfully added income";
		}catch(Exception e) {
			e.printStackTrace();
			return "Error adding income:\n" + e;
		}
	}

	@Override
	public String deleteSelectedIncome(int incomeId) {
		try {
			incomeRepository.deleteById(incomeId);
			return "Successfully deleted income";
		}catch(Exception e) {
			e.printStackTrace();
			return "Error deleting income:\n" + e;
		}
	}

	@Override
	public IncomeModel getIncomeById(int incomeId) {
		IncomeEntity entity = incomeRepository.findById(incomeId);
		return new IncomeModel(entity.getId(), entity.getDescription(), entity.getAmount(), entity.getDate(), entity.getNotes(), entity.getUserId());
	}

	@Override
	public String updateSelectedIncome(IncomeModel income) {
		IncomeEntity incomeEntity = new IncomeEntity(income.getId(), income.getDescription(), income.getAmount(), income.getDate(), income.getNotes(), income.getUserId());
		try {
			incomeRepository.update(incomeEntity);
			return "Successfully updated income";
		}catch(Exception e) {
			e.printStackTrace();
			return "Error updating income:\n" + e;
		}
		
	}

	
}
