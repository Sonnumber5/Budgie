package com.jackp.business;

import java.sql.Date;
import java.util.List;

import com.jackp.entity.IncomeEntity;

public interface IncomeBusinessInterface {
	public List<IncomeEntity> findAllDesc(int userId);
	public List<IncomeEntity> searchForKeyWordDesc(int userId, String keyword);
	public List<IncomeEntity> searchByDateDesc(int userId, Date date);
}
