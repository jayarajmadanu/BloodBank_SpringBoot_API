package com.examly.BloodBank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examly.BloodBank.model.AvailableBloodResults;
import com.examly.BloodBank.model.BloodBank;
import com.examly.BloodBank.model.Donars;
import com.examly.BloodBank.model.User;
import com.examly.BloodBank.servises.servises;
import com.examly.BloodBank.utils.Utils;

@Controller
@CrossOrigin
public class controller {
	
	@Autowired
	servises servise;
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody Map<String, String> userMap) {
		String email = userMap.get("email");
		String password = userMap.get("password");
		User user = servise.validateUser(email,password);
		
		//return new ResponseEntity<String>(Utils.generateJWT(user), HttpStatus.OK);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		//return new ResponseEntity<String>(Utils.generateJWT(user), HttpStatus.OK);
		user = servise.addUser(user); 
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping(value = "/getAvailableBlood", produces = "application/json")
	public ResponseEntity<Map<String,List<AvailableBloodResults>>> getBloodByStateAndType(@RequestBody Map<String, String> stateTypeMap){
		String state = stateTypeMap.get("state");
		String type = stateTypeMap.get("type");
		List<AvailableBloodResults> res = servise.getBloodByStateAndType(state, type);
		Map< String, List<AvailableBloodResults> > data = new HashMap<>();
		data.put("results", res);
		return new ResponseEntity<>(data,HttpStatus.OK);
	}
	
	@PostMapping(value="/getBloodBanks")
	public ResponseEntity<Map<String,List<BloodBank>>> getBloodBanks(@RequestBody Map<String, String> stateMap){
		String state = stateMap.get("state");
		List<BloodBank> res = servise.getBloodBanks(state);
		Map< String, List<BloodBank> > data = new HashMap<>();
		data.put("results", res);
		return new ResponseEntity<>(data,HttpStatus.OK);
		
	}
	
	@PostMapping("/getUser")
	public ResponseEntity<Map<String,User>> getUserById(@RequestBody() Map<String, Long> idMap){
		long id = idMap.get("userId");
		User u = servise.getUserById(id);
		Map< String, User > data = new HashMap<>();
		data.put("user", u);
		return new ResponseEntity<Map<String,User>>(data, HttpStatus.OK);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<Map<String, List<User>>> getAllUsers(){
		List<User> users = servise.getAllUsers();
		Map<String, List<User>> data = new HashMap<>();
		data.put("users", users);
		return new ResponseEntity<>(data,HttpStatus.OK);
	} 
	
	@PostMapping("/updateUser")
	public ResponseEntity<Map<String,User>> updateUser(@RequestBody() Map<String, String> userMap){
		long id = Long.parseLong(userMap.get("userId"));
		String firstName = userMap.get("firstName");
		String lastName = userMap.get("lastName");
		String password = userMap.get("password");
		
		User u = servise.updateUser(id, firstName, lastName, password);
		Map< String, User > data = new HashMap<>();
		data.put("user", u);
		return new ResponseEntity<Map<String,User>>(data, HttpStatus.OK);
	}
	
	@PostMapping("/addOrRemoveAdmin")
	@ResponseBody
	public String addOrRemoveAdmin(@RequestBody Map<String, String> map) {
		long id = Long.parseLong(map.get("id"));
		boolean admin = Boolean.parseBoolean(map.get("admin"));
		int rows= servise.addOrRemoveAdmin(id, admin);
		if(rows==0) return "admin not updated";
		return "admin updated";
		
	}
	@PostMapping("/deleteUser")
	@ResponseBody
	public String deleteUserById(@RequestBody Map<String, String> map) {
		long id = Long.parseLong(map.get("id"));
		int rows= servise.deleteUserById(id);
		if(rows==0) return "user not deleted";
		return "user deleted";
	}
	
	@PostMapping("/getUserByName")
	public ResponseEntity<Map<String, List<User>>> getUsersByName(@RequestBody Map<String, String> map){
		String name = map.get("name");
		List<User> users = servise.getUsersByName(name);
		Map< String, List<User> > data = new HashMap<>();
		data.put("users", users);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@PostMapping("/updateBloodBank")
	@ResponseBody
	public String updateBloodBank(@RequestBody Map<String, String> bankMap) {
		Long id = Long.parseLong(bankMap.get("id"));
		String name = bankMap.get("name");
		String address = bankMap.get("address");
		String state = bankMap.get("state");
		String contact = bankMap.get("contact");
		System.out.println(id+name+address+state+contact);
		servise.updateBloodBank(id,name,address,state,contact);
		return "blood bank updated";
	}
	
	@PostMapping("/getBloodBank")
	public ResponseEntity<Map<String,BloodBank>> getBloodBankById( @RequestBody Map<String, Long> idMap ){
		long id = idMap.get("id");
		System.out.println(id);
		BloodBank bank = servise.getBloodBankById(id);
		Map< String, BloodBank > data = new HashMap<>();
		data.put("bank", bank);
		return  new ResponseEntity<Map<String,BloodBank>>(data, HttpStatus.OK);
	}
	
	@PostMapping("/updateBlood")
	@ResponseBody
	public String updateBloodByIdAndType(@RequestBody Map<String, String> map){
		Long bankId = Long.parseLong(map.get("id"));
		String type= map.get("type");
		double amount = Double.parseDouble(map.get("amount"));
		servise.updateBloodByIdAndType(bankId,type,amount);
		return "blood updated";
	}
	
	@PostMapping("/getDonars")
	public ResponseEntity< Map<String, List<Donars>> > getAllDonars(@RequestBody Map<String, String> map){
		String state = map.get("state");
		String type = map.get("type");
		List<Donars> donars = servise.getAllDonars(state,type);
		Map<String, List<Donars>> data = new HashMap<>();
		data.put("donars",donars);
		return new ResponseEntity<Map<String,List<Donars>>>(data, HttpStatus.OK);
		
	}
	
	@PostMapping("/deleteDonar")
	@ResponseBody
	public String deleteDonar(@RequestBody Map<String, String> map) {
		Long id = Long.parseLong(map.get("id"));
		servise.deleteDonar(id);
		return "donar deleted";
	}

}
