package com.movilitas.connect.freshworks.freshdesk.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TicketChange {
	
	public static enum ChangeType {
		FreshdeskTicketIdChange,
		AgentChange,
		StateChange,
		DeletedChange,
		TicketTypeChange
	  }
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  //Keep here the properties that might be useful to do reporting and tracing  
	private int id; //this is the DB id
	private String freshdeskId;
	private ChangeType changeType;
	private String oldValue;
	private String newValue;
	private Date timestamp;
	private Date ticketCreationTimestamp;
	
	public TicketChange() {}
	
	public TicketChange(String freshdeskId, Date ticketCreationTimestamp,ChangeType changeType, String oldValue,String newValue) {
		this.timestamp=new Date();
		this.ticketCreationTimestamp=ticketCreationTimestamp;
		this.changeType=changeType;
		this.freshdeskId=freshdeskId;
		this.oldValue=oldValue;
		this.newValue=newValue;
				
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
	public ChangeType getChangeType() {
		return changeType;
	}
	public void setChangeType(ChangeType changeType) {
		this.changeType = changeType;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getTicketCreationTimestamp() {
		return ticketCreationTimestamp;
	}

	public void setTicketCreationTimestamp(Date ticketCreationTimestamp) {
		this.ticketCreationTimestamp = ticketCreationTimestamp;
	}
	
	
	
}
