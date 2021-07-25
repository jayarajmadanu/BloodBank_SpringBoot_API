package com.examly.BloodBank.utils;

import java.util.Date;
import java.util.Map;


import com.examly.BloodBank.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Utils {
	static String API_SECRET_KEY ="secret";
	public static String generateJWT(User user){
		long timestamp = System.currentTimeMillis();
		        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, API_SECRET_KEY)
		                .setIssuedAt(new Date(timestamp))
		                .claim("id", user.getId())
		                .claim("email", user.getEmail())
		                .claim("firstName", user.getFirstName())
		                .claim("lastName", user.getLastName())
		                .compact();
		        return  token;
	}
 
}
