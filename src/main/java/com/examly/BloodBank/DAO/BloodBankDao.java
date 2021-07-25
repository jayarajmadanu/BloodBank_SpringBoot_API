package com.examly.BloodBank.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.examly.BloodBank.Exceptions.InvAuthException;
import com.examly.BloodBank.RowMapper.AvailableBloodRowMapper;
import com.examly.BloodBank.RowMapper.BloodBankRowMapper;
import com.examly.BloodBank.RowMapper.DonarRowMapper;
import com.examly.BloodBank.RowMapper.UserRowMapper;
import com.examly.BloodBank.model.AvailableBloodResults;
import com.examly.BloodBank.model.BloodBank;
import com.examly.BloodBank.model.Donars;
import com.examly.BloodBank.model.User;

@Repository
public class BloodBankDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public User validate(String email, String password) throws InvAuthException{
		// TODO Auto-generated method stub
		String sql = "select * from users where email = ?";
		Object args[]= {email};
		try {
			User user = jdbcTemplate.queryForObject(sql, args, new UserRowMapper());
			if(user.getPassword().equals(password))
				return user;
			else throw new InvAuthException("Invalid password");
		}
		catch(EmptyResultDataAccessException e) {
			throw new InvAuthException("Invalid email");
		}
	}

	public User addUser(User user) throws InvAuthException{
		// TODO Auto-generated method stub
		String sql = "insert into users(firstName, lastName, email, password) values(?,?,?,?)";
		Object args[] = {user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()};
		
		try {
			 int x = jdbcTemplate.update(sql, args);
			 System.out.println(x);
			 return validate(user.getEmail(), user.getPassword());
		}
		catch(Exception e) {
			throw new InvAuthException("unable to register");
		}
		
	}

	@SuppressWarnings("deprecation")
	public List<AvailableBloodResults> getBloodByStateAndType(String state, String type) {
		// TODO Auto-generated method stub
		String sql ="";
		List<AvailableBloodResults> res;
		if(state.equals("*") && type.equals("*")) {
			sql = "select "
					+ "id, name, address, contact, state, amount, type "
					+ "from bloodbank join availableblood "
					+ "on id = bloodBankId";
			res = jdbcTemplate.query(sql, new AvailableBloodRowMapper());
		}
		else if(!type.equals("*") && state.equals("*") ) {
			sql = "select "
					+ "id, name, address, contact, state, amount, type  "
					+ "from bloodbank join availableblood "
					+ "on id = bloodBankId where type = ?";
			Object args[]= {type};
			res = jdbcTemplate.query(sql, args, new AvailableBloodRowMapper());
		}
		else if(!state.equals("*") && type.equals("*")) {
			sql = "select "
					+ "id, name, address, contact, state, amount, type  "
					+ "from bloodbank join availableblood "
					+ "on id = bloodBankId where state = ?";
			Object args[]= {state};
			res = jdbcTemplate.query(sql, args, new AvailableBloodRowMapper());
		}
		else {
			sql = "select "
					+ "id, name, address, contact, state, amount, type  "
					+ "from bloodbank join availableblood "
					+ "on id = bloodBankId where type = ? and state = ? ";
			Object args[]= {type,state};
			res = jdbcTemplate.query(sql, args, new AvailableBloodRowMapper());
		}
		
		return res;
	}

	public List<BloodBank> getBloodBanks(String state) {
		// TODO Auto-generated method stub
		String sql;
		List<BloodBank> res;
		
			if(state.equals("*")) {
				sql = "select * from bloodbank";
				res = jdbcTemplate.query(sql, new BloodBankRowMapper());
			}
			else {
				sql = "select * from bloodbank where state =?";
				Object args[]= {state};
				res = jdbcTemplate.query(sql, args, new BloodBankRowMapper());
			}
		
		return res;
	}

	public User getUserById(long id) {
		// TODO Auto-generated method stub
		String sql = "select * from users where id = ?";
		User u = jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
		return u;
	}

	public User updateUser(long id, String firstName, String lastName, String password) {
		// TODO Auto-generated method stub
		String sql = "update users set firstName=?, lastName=?, password=?  where id =?";
		Object args[]= {firstName, lastName, password, id};
		 jdbcTemplate.update(sql, args);
		 User u = getUserById( id);
		return u;
	}

	public void updateBloodBank(Long id, String name, String address, String state, String contact) {
		// TODO Auto-generated method stub
		String sql ="update bloodbank set name=?, address=?, state=?, contact=? where id =?";
		Object args[]= {name,  address,  state,  contact,id};
		jdbcTemplate.update(sql, args);
	}

	public BloodBank getBloodBankById(long id) {
		// TODO Auto-generated method stub
		String sql = " select * from bloodbank where id = ?";
		
		return jdbcTemplate.queryForObject(sql,  new BloodBankRowMapper(), id);
	}

	public void updateBloodByIdAndType(Long bankId, String type, double amount) {
		// TODO Auto-generated method stub
		String sql = "update  availableblood set amount = ? where bloodBankId = ? and type =?";
		Object args[] = {amount, bankId, type};
		jdbcTemplate.update(sql, args);
		
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		String sql = "select * from users";
		
		return jdbcTemplate.query(sql, new UserRowMapper());
	}

	public int addOrRemoveAdmin(long id, boolean admin) {
		// TODO Auto-generated method stub
		String sql = "update users set admin =? where id =?";
		Object args[]= {admin,id};
		return jdbcTemplate.update(sql, args);
	}

	public int deleteUserById(Long id) {
		// TODO Auto-generated method stub
		String sql = "delete from users where id=?";
		return jdbcTemplate.update(sql,id);
	}

	public List<User> getUsersByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select * from users where firstName=?";
		return jdbcTemplate.query(sql, new UserRowMapper(), name);
	}

	@SuppressWarnings("deprecation")
	public List<Donars> getAllDonars(String state, String type) {
		String sql ="";
		List<Donars> res;
		if(state.equals("*") && type.equals("*")) {
			sql = "select * from donars";
			res = jdbcTemplate.query(sql, new DonarRowMapper());
		}
		else if(!type.equals("*") && state.equals("*") ) {
			sql = "select * from donars where type = ?";
			Object args[]= {type};
			res = jdbcTemplate.query(sql, args, new DonarRowMapper());
		}
		else if(!state.equals("*") && type.equals("*")) {
			sql = "select * from donars where state = ?";
			Object args[]= {state};
			res = jdbcTemplate.query(sql, args, new DonarRowMapper());
		}
		else {
			sql = "select * from donars where type = ? and state = ? ";
			Object args[]= {type,state};
			res = jdbcTemplate.query(sql, args, new DonarRowMapper());
		}
		
		return res;
	}

	public void deleteDonar(Long id) {
		// TODO Auto-generated method stub
		String sql = "delete from donars where id=?";
		 jdbcTemplate.update(sql,id);
		
	}

}
