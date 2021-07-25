package com.examly.BloodBank.model;

public class AvailableBlood {
	
	private long bloodBankId;
	private String type;
	private float amount;
	
	public long getBloodBankId() {
		return bloodBankId;
	}
	public void setBloodBankId(long bloodBankId) {
		this.bloodBankId = bloodBankId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	

}
