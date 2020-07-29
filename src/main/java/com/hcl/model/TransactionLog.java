package com.hcl.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "TransactionLog")//specifying table name to be created
public class TransactionLog {

	@Id // Specifying primary key for id attribute
	@GeneratedValue(strategy = GenerationType.IDENTITY)//specifying auto increment for id attribute
	private long id;

	private long uid;
	
	private String updateDateTime;
	
	private Double debitedAmount;
	//getters and setters for the above attributes 
	public Double getDebitedAmount() {
		return debitedAmount;
	}
	public void setDebitedAmount(Double debitedAmount) {
		this.debitedAmount = debitedAmount;
	}
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	
	
	
}
