package com.jackp.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.jackp.entity.UserEntity;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    
    public UserRepository(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(UserEntity user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        try {
            jdbcTemplate.update("INSERT INTO users (firstname, lastname, username, password, email) VALUES (?, ?, ?, ?, ?)",
                    user.getFirstName(), user.getLastName(), user.getUserName(), hashedPassword, user.getEmail());
            return "Registration Successful";
        }catch(Exception e) {
        	e.printStackTrace();
    		return "Error Registering" + e.getMessage();
    	}

    }

    public UserEntity findByUsername(String userName) {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE username = ?",
                (rs, rowNum) -> new UserEntity(
                    rs.getInt("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email")
                ),
                userName
            );
        } catch (Exception e) {
        	
            return null;
        }
    }
}
