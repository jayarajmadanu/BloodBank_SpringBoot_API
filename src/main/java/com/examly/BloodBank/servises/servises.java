package com.examly.BloodBank.servises;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.BloodBank.DAO.BloodBankDao;
import com.examly.BloodBank.Exceptions.InvAuthException;
import com.examly.BloodBank.model.AvailableBloodResults;
import com.examly.BloodBank.model.BloodBank;
import com.examly.BloodBank.model.Donars;
import com.examly.BloodBank.model.User;

@Service
public class servises {
	
	@Autowired
	BloodBankDao bloodBankDao;

	public User validateUser(String email, String password){
		
		return bloodBankDao.validate(email,password);
	}

	public User addUser(User user) {
		// TODO Auto-generated method stub
		return bloodBankDao.addUser(user);
	}

	public List<AvailableBloodResults> getBloodByStateAndType(String state, String type) {
		// TODO Auto-generated method stub
		return bloodBankDao.getBloodByStateAndType(state, type);
	}

	public List<BloodBank> getBloodBanks(String state) {
		// TODO Auto-generated method stub
		return bloodBankDao.getBloodBanks(state);
	}

	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return bloodBankDao.getUserById(id);
	}

	public User updateUser(long id, String firstName, String lastName, String password) {
		// TODO Auto-generated method stub
		return bloodBankDao.updateUser( id,  firstName,  lastName,  password);
	}

	public void updateBloodBank(Long id, String name, String address, String state, String contact) {
		// TODO Auto-generated method stub
		bloodBankDao.updateBloodBank( id,  name,  address,  state,  contact);
	}

	public BloodBank getBloodBankById(long id) {
		// TODO Auto-generated method stub
		return bloodBankDao.getBloodBankById( id);
	}

	public void updateBloodByIdAndType(Long bankId, String type, double amount) {
		// TODO Auto-generated method stub
		bloodBankDao.updateBloodByIdAndType( bankId,  type, amount);
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return bloodBankDao.getAllUsers();
	}

	public int addOrRemoveAdmin(long id, boolean admin) {
		// TODO Auto-generated method stub
		return bloodBankDao.addOrRemoveAdmin( id,  admin);
	}

	public int deleteUserById(Long id) {
		// TODO Auto-generated method stub
		return bloodBankDao.deleteUserById( id);
	}

	public List<User> getUsersByName(String name) {
		// TODO Auto-generated method stub
		return bloodBankDao. getUsersByName( name);
	}

	public List<Donars> getAllDonars(String state, String type) {
		// TODO Auto-generated method stub
		return bloodBankDao.getAllDonars( state,  type);
	}

	public void deleteDonar(Long id) {
		// TODO Auto-generated method stub
		bloodBankDao.deleteDonar( id);
	}

}
