package com.jackp.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jackp.entity.ExpenseEntity;

@Repository
public class ExpenseRepository {
    private final JdbcTemplate jdbcTemplate;
    
    public ExpenseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(ExpenseEntity expense) {
    	String sql = "INSERT INTO expenses (description, amount, category, date, notes, userId) VALUES (?,?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql, expense.getDescription(), expense.getAmount(), expense.getCategory(), expense.getDate(), expense.getNotes(), expense.getUserId());
        }catch(Exception e) {
        	e.printStackTrace();
    	}

    }
    
    public List<ExpenseEntity> findByDateDescOrder(int expenseId, String date) {
        String sql = "SELECT * FROM expenses WHERE userId = ? AND DATE_FORMAT(date, '%Y-%m') = ? ORDER BY date DESC";

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ExpenseEntity.class), expenseId, date);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    public List<ExpenseEntity> findBySearch(String searchFilter, int userId) {
        String sql = "SELECT * FROM expenses WHERE userId = ? AND Description like ? ORDER BY date DESC";

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ExpenseEntity.class), userId, "%" + searchFilter.toLowerCase() + "%");
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

	public void deleteById(int expenseId) {
		String sql = "DELETE FROM expenses WHERE ID = ?";
		
		try {
			jdbcTemplate.update(sql, expenseId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public ExpenseEntity findById(int expenseId) {
		String sql = "SELECT * FROM expenses WHERE ID = ?";
		try {
			return (ExpenseEntity) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ExpenseEntity.class), expenseId);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void update(ExpenseEntity expenseEntity) {
		String sql = "UPDATE expenses SET description = ?, amount = ?, category = ?, date = ?, notes = ? WHERE ID = ?";
		try {
			jdbcTemplate.update(sql, expenseEntity.getDescription(), expenseEntity.getAmount(), expenseEntity.getCategory(), expenseEntity.getDate(), expenseEntity.getNotes(), expenseEntity.getId());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public BigDecimal findSumByDate(String date, int userId) {
		String sql = "SELECT SUM(amount) FROM expenses where userId = ? AND DATE_FORMAT(date, '%Y-%m') = ?";
		try {
			BigDecimal sum = jdbcTemplate.queryForObject(sql, BigDecimal.class, userId, date);
			return sum;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
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
