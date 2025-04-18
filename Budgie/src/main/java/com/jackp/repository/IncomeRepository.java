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

    public String insertIncome(IncomeEntity income) {
    	String sql = "INSERT INTO income (description, amount, date, notes, users_ID) VALUES (?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql,income.getDescription(), income.getAmount(), income.getDate(), income.getNotes(), income.getUserId());
            return "Registration Successful";
        }catch(Exception e) {
        	e.printStackTrace();
    		return "Error Registering" + e.getMessage();
    	}

    }
    
    public List<IncomeEntity> findByDateDescOrder(int userId, String date) {
        String sql = "SELECT * FROM income WHERE users_ID = ? AND DATE_FORMAT(date, '%Y-%m') = ? ORDER BY date DESC";

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(IncomeEntity.class), userId, date);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    public List<IncomeEntity> findBySearch(String searchFilter, int userId) {
        String sql = "SELECT * FROM income WHERE users_ID = ? AND Description like ? ORDER BY date DESC";

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(IncomeEntity.class), userId, "%" + searchFilter.toLowerCase() + "%");
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
