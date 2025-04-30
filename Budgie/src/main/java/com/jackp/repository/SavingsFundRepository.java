package com.jackp.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jackp.entity.SavingsFundEntity;

@Repository
public class SavingsFundRepository {
    private final JdbcTemplate jdbcTemplate;
    
    public SavingsFundRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(SavingsFundEntity fund) {
    	String sql = "INSERT INTO savingsFunds (name, goal, amount, userId) VALUES (?,?,?,?)";
        try {
            jdbcTemplate.update(sql, fund.getName(), fund.getGoal(), fund.getAmount(), fund.getUserId());
        }catch(Exception e) {
        	e.printStackTrace();
    	}

    }
    
    public List<SavingsFundEntity> findAllFunds(int userId) {
        String sql = "SELECT * FROM savingsFunds WHERE userId = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SavingsFundEntity.class), userId);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    

	public void deleteById(int savingsFundId) {
		String sql = "DELETE FROM savingsFunds WHERE ID = ?";
		
		try {
			jdbcTemplate.update(sql, savingsFundId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public SavingsFundEntity findById(int savingsFundId) {
		String sql = "SELECT * FROM savingsFunds WHERE ID = ?";
		try {
			return (SavingsFundEntity) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(SavingsFundEntity.class), savingsFundId);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void update(SavingsFundEntity entity) {
		String sql = "UPDATE savingsFunds SET name = ?, goal = ?, amount = ? WHERE ID = ?";
		try {
			jdbcTemplate.update(sql, entity.getName(), entity.getGoal(), entity.getAmount(), entity.getId());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<String> findAllCategoryNames(int sessionUserId){
		String sql = "SELECT DISTINCT category FROM expenses WHERE userId = ?";
		try {
			return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("category"), sessionUserId);
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
