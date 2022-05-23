package com.movilitas.connect.freshworks.freshdesk.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  //Keep here the properties that might be useful to do reporting and tracing  
	private int id; //this is the DB id
	private String freshdeskId; //this is the DB id
	private String ticketType; //this is the DB id
	private int status;
	private boolean isDeleted;
	private Date createdAt;
	
	@ManyToOne
	private Agent agent;
	
	
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
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
		
}
