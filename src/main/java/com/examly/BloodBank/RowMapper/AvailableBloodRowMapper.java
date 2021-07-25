package com.examly.BloodBank.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.examly.BloodBank.model.AvailableBloodResults;

public class AvailableBloodRowMapper implements RowMapper<AvailableBloodResults> {

	@Override
	public AvailableBloodResults mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		AvailableBloodResults res = new AvailableBloodResults();
		res.setAddress(rs.getString("address"));
		res.setAmount(rs.getFloat("amount"));
		res.setContact(rs.getString("contact"));
		res.setId(rs.getLong("id"));
		res.setName(rs.getString("name"));
		res.setState(rs.getString("state"));
		res.setType(rs.getString("type"));
		return res;
	}

}
