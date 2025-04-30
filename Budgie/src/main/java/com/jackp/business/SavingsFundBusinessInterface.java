package com.jackp.business;

import java.util.List;

import com.jackp.entity.SavingsFundEntity;
import com.jackp.model.SavingsFundModel;


public interface SavingsFundBusinessInterface {
	public List<SavingsFundModel> getAllFunds(int userId);
	public String addSavingsFund(SavingsFundModel fund);
	public String deleteSelectedFund(int fundId);
	public SavingsFundModel getSavingsFundById(int fundId);
	public String updateSelectedSavingsFund(SavingsFundModel fund);
	public SavingsFundModel entityToModelMapper(SavingsFundEntity entity);
	public SavingsFundEntity modelToEntityMapper(SavingsFundModel model);
}
