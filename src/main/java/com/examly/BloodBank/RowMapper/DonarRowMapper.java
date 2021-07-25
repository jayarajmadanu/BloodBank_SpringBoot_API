package com.examly.BloodBank.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.examly.BloodBank.model.Donars;

public class DonarRowMapper implements RowMapper<Donars> {

	@Override
	public Donars mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Donars d = new Donars();
		d.setAge(rs.getInt("age"));
		d.setId(rs.getLong("id"));
		d.setContact(rs.getString("contact"));
		d.setGender(rs.getString("gender"));
		d.setName(rs.getString("name"));
		d.setState(rs.getString("state"));
		d.setType(rs.getString("type"));
		return d;
	}

}
