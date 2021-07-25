package com.examly.BloodBank.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;            

import com.examly.BloodBank.model.User;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setEmail(rs.getString("email"));
		user.setAdmin(rs.getBoolean("admin"));
		user.setPassword(rs.getString("password"));
		return user;
	}

}
