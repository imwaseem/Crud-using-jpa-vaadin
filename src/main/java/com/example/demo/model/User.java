package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {


	@Id
	private int oid;
	private String name;
	private String phoneNumber;
	private String address;
	
	public User(){
		
	}
	public User(int oid, String name, String phoneNumber, String address) {
		super();
		this.oid = oid;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		address = address;
	}
	
}
