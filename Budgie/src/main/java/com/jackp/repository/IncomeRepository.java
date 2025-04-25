package com.jackp.repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jackp.entity.IncomeEntity;

@Repository
public class IncomeRepository {
    private final JdbcTemplate jdbcTemplate;
    
    public IncomeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(IncomeEntity income) {
    	String sql = "INSERT INTO income (description, amount, date, notes, userId) VALUES (?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql,income.getDescription(), income.getAmount(), income.getDate(), income.getNotes(), income.getUserId());
        }catch(Exception e) {
        	e.printStackTrace();
    	}

    }
    
    public List<IncomeEntity> findByDateDescOrder(int incomeId, String date) {
        String sql = "SELECT * FROM income WHERE userId = ? AND DATE_FORMAT(date, '%Y-%m') = ? ORDER BY date DESC";

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(IncomeEntity.class), incomeId, date);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    public List<IncomeEntity> findBySearch(String searchFilter, int userId) {
        String sql = "SELECT * FROM income WHERE userId = ? AND Description like ? ORDER BY date DESC";

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(IncomeEntity.class), userId, "%" + searchFilter.toLowerCase() + "%");
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

	public void deleteById(int incomeId) {
		String sql = "DELETE FROM income WHERE ID = ?";
		
		try {
			jdbcTemplate.update(sql, incomeId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public IncomeEntity findById(int incomeId) {
		String sql = "SELECT * FROM income WHERE ID = ?";
		try {
			return (IncomeEntity) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(IncomeEntity.class), incomeId);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void update(IncomeEntity incomeEntity) {
		String sql = "UPDATE income SET description = ?, amount = ?, date = ?, notes = ? WHERE ID = ?";
		try {
			jdbcTemplate.update(sql, incomeEntity.getDescription(), incomeEntity.getAmount(), incomeEntity.getDate(), incomeEntity.getNotes(), incomeEntity.getId());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
