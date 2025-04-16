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
	public List<IncomeModel> getAllByDateDesc(int userId, LocalDate date) {
		List<IncomeEntity> incomes = incomeRepository.findByDateDescOrder(userId, date);
		List<IncomeModel> result = new ArrayList<>();
		
		for (IncomeEntity entity : incomes) {
			IncomeModel model = new IncomeModel(entity.getId(), entity.getDescription(), entity.getAmount(), entity.getDate(), entity.getNotes(), entity.getUserId());
			result.add(model);
		}
		
		return result;
	}

	@Override
	public BigDecimal getIncomeSumByDate(int userId, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
