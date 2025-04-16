package com.jackp.business;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.jackp.model.IncomeModel;

public interface IncomeBusinessInterface {
	public List<IncomeModel> getAllByKeyWordDesc(int userId, String keyword);
	public List<IncomeModel> getAllByDateDesc(int userId, LocalDate date);
	public BigDecimal getIncomeSumByDate(int userId, LocalDate date);
}
