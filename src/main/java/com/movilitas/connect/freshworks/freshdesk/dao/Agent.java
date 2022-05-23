package com.movilitas.connect.freshworks.freshdesk.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Agent {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  //Keep here the properties that might be useful to do reporting and tracing  
	private int id; //this is the DB id
	private String freshdeskId; //this is the DB id
	private String name; //this is the DB id
	private int level;
	
	public Agent() {}
	
	public Agent(String freshdeskId, String name, int level) {
		setFreshdeskId(freshdeskId);
		setName(name);
		setLevel(level);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFreshdeskId() {
		return freshdeskId;
	}
	public void setFreshdeskId(String freshdeskId) {
		this.freshdeskId = freshdeskId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
}
