package com.examly.BloodBank.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.examly.BloodBank.model.BloodBank;

public class BloodBankRowMapper implements RowMapper<BloodBank> {

	@Override
	public BloodBank mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		BloodBank bank = new BloodBank();
		bank.setId(rs.getLong("id"));
		bank.setAddress(rs.getString("address"));
		bank.setContact(rs.getString("contact"));
		bank.setName(rs.getString("name"));
		bank.setState(rs.getString("state"));
		return bank;
	}

}
