package com.jackp.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jackp.entity.SavingsFundEntity;
import com.jackp.model.SavingsFundModel;
import com.jackp.repository.SavingsFundRepository;

@Service
public class SavingsFundBusinessService implements SavingsFundBusinessInterface{

	private final SavingsFundRepository savingsFundRepository;
	
	public SavingsFundBusinessService(SavingsFundRepository savingsFundRepository) {
		this.savingsFundRepository = savingsFundRepository;
	}
	
	@Override
	public List<SavingsFundModel> getAllFunds(int userId) {
		try {
			List<SavingsFundEntity> entities = savingsFundRepository.findAllFunds(userId);
			List<SavingsFundModel> models = new ArrayList<>();
			
			for (SavingsFundEntity entity : entities) {
				models.add(entityToModelMapper(entity));
			}
			return models;
		}catch(Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public String addSavingsFund(SavingsFundModel fund) {
		SavingsFundEntity entity = modelToEntityMapper(fund);
		try {
			savingsFundRepository.insert(entity);
			return "Successfully created savings fund";
		}catch(Exception e) {
			e.printStackTrace();
			return "Failed to add Savings Fund";
		}
	}

	@Override
	public String deleteSelectedFund(int fundId) {
		try {
			savingsFundRepository.deleteById(fundId);
			return "Succesfully deleted savings fund";
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public SavingsFundModel getSavingsFundById(int fundId) {
		try {
			SavingsFundEntity entity = savingsFundRepository.findById(fundId);
			return entityToModelMapper(entity);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String updateSelectedSavingsFund(SavingsFundModel fund) {
		SavingsFundEntity entity = modelToEntityMapper(fund);
		try {
			savingsFundRepository.update(entity);
			return "Successfully updated Savings Fund";
		}catch(Exception e) {
			e.printStackTrace();
			return "Failed to update Savings Fund";
		}
	}

	@Override
	public SavingsFundModel entityToModelMapper(SavingsFundEntity entity) {
		return new SavingsFundModel (entity.getId(), entity.getName(), entity.getGoal(), entity.getAmount(), entity.getUserId());
	}

	@Override
	public SavingsFundEntity modelToEntityMapper(SavingsFundModel model) {
		
		if(model.getId() == 0){
			return new SavingsFundEntity (model.getName(), model.getGoal(), model.getAmount(), model.getUserId());
		}
		else {
			return new SavingsFundEntity (model.getId(), model.getName(), model.getGoal(), model.getAmount(), model.getUserId());
		}

	}

}
