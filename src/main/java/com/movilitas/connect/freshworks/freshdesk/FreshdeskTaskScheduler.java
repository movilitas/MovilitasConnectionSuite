package com.movilitas.connect.freshworks.freshdesk;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.movilitas.connect.freshworks.freshdesk.dao.Agent;
import com.movilitas.connect.freshworks.freshdesk.dao.AgentRepository;
import com.movilitas.connect.freshworks.freshdesk.dao.Ticket;
import com.movilitas.connect.freshworks.freshdesk.dao.TicketChange;
import com.movilitas.connect.freshworks.freshdesk.dao.TicketChangeRepository;
import com.movilitas.connect.freshworks.freshdesk.dao.TicketRepository;
import com.movilitas.connect.freshworks.freshdesk.json.AgentJson;
import com.movilitas.connect.freshworks.freshdesk.json.TicketJson;

@Service
public class FreshdeskTaskScheduler {

	@Autowired
	FreshdeskService freshdeskService;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketChangeRepository ticketChangeRepository;
	
	@Autowired
	AgentRepository agentRepository;
	
	@EventListener(ApplicationReadyEvent.class)
	public void runAtStart() throws ParseException {
		System.out.println("Starting FRESHDESK SCHEDULER (cron = */30 * * * * * )");
		checkForFreshdeskChanges();
	}
	
	int counter=0;
	@Scheduled(cron = "*/30 * * * * *")  //Every 30 sec. so freshdesk changes are recorded quite accurate
	public void checkForFreshdeskChanges() throws ParseException {
		counter++;
		System.out.println( LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)+" "+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)+" : START SYNC "+counter);
		
		
			TicketJson[] fdTickets=freshdeskService.getTicketsUpdatedSince(LocalDate.now().minusDays(8)); //take only the freshdesk the tickets that changed in the last three day's ... to make sure we cover a weekend downtimes..
			System.out.println("\nTickets returned. Qty : "+fdTickets.length);
			
			int c=0;
			//Check for each json if something changed
			for(TicketJson fdTicket:fdTickets) {
				c++;
				//System.out.println("Checking fdTicket "+fdTicket.getId());
				
				//Make sure the agent exists.
				String agentId=fdTicket.getResponderId();
				if(agentId!=null && !agentId.isEmpty()) { //there is actually an agent specified
					boolean exists = agentRepository.existsByFreshdeskId(agentId);
					if(!exists) {
						Agent agent=new Agent();
						agent.setFreshdeskId(fdTicket.getResponderId());
						AgentJson agentJson=freshdeskService.getAgentFromFreshdesk(fdTicket.getResponderId());
						agent.setName(agentJson.getContact().getName());
						agent.setLevel(getLevel(agentJson.getContact().getName()));
						agentRepository.save(agent);
					}
				}else {
					fdTicket.setResponderId(""); //don't keep in NULL
				}
				
				Ticket ticket=ticketRepository.findByFreshdeskId(fdTicket.getId());
				
				if(ticket==null) { //this is a new ticket
					ticket=new Ticket();
					ticket.setFreshdeskId(fdTicket.getId());
					
					String strDate=fdTicket.getCreatedAt(); //This is  e.g. 2021-10-11T06:05:57Z
					LocalDateTime ldt = LocalDateTime.parse(strDate,DateTimeFormatter.ISO_DATE_TIME);
					
					Date createdDate = Date.from(ldt.atZone(ZoneId.of("Z")).toInstant()); //Save the ZULU time in the DB
					ticket.setCreatedAt(createdDate);
					ticket=ticketRepository.save(ticket);
					
					TicketChange change=new TicketChange(
							fdTicket.getId(),
							createdDate,
							TicketChange.ChangeType.FreshdeskTicketIdChange,
							"",
							fdTicket.getId()
						);
					ticketChangeRepository.save(change);
				}
				
				
				if(!fdTicket.getResponderId().isEmpty()) { //don't change the last saved responder if this is empty.
					//Check an agent change based on responderId
					if(ticket.getAgent()==null || !ticket.getAgent().getFreshdeskId().equals(fdTicket.getResponderId())) {
						updateAgent(ticket,fdTicket);
					}

				}
				
				if(ticket.getStatus()!=fdTicket.getStatus()) {
					String oldValue=Integer.toString(ticket.getStatus());
					ticket.setStatus(fdTicket.getStatus());
					ticketRepository.save(ticket);
					
					TicketChange change=new TicketChange(
							fdTicket.getId(),
							ticket.getCreatedAt(),
							TicketChange.ChangeType.StateChange,
							oldValue,
							fdTicket.getStatus().toString()
						);
					
					ticketChangeRepository.save(change);
				}
				
				//Check for TicketType Change
				
//				if(fdTicket.getDeleted()==null || !fdTicket.getDeleted()) {
//					System.out.println(c+ " Ticket "+fdTicket.getId()+" : "+fdTicket.getType()+" "+ticket.getTicketType());
//				}
				String fdType=fdTicket.getType();
				String dbType=ticket.getTicketType();
				
				String oldTypeValue=dbType;
				if(fdType!=null) { //fdType is not null
					if(!fdType.equals(dbType)) {
						
						ticket.setTicketType(fdType);
						ticketRepository.save(ticket);
						
						TicketChange change=new TicketChange(
								fdTicket.getId(),
								ticket.getCreatedAt(),
								TicketChange.ChangeType.TicketTypeChange,
								oldTypeValue,
								fdType
							);
						ticketChangeRepository.save(change);
					}
					
					
				}else { //fdType is null
					if(ticket.getTicketType()!=null) { 
						System.out.println("Changing type to "+fdType);
						ticket.setTicketType(fdType);
						ticketRepository.save(ticket);
						
						TicketChange change=new TicketChange(
								fdTicket.getId(),
								ticket.getCreatedAt(),
								TicketChange.ChangeType.TicketTypeChange,
								oldTypeValue,
								fdType
							);
						ticketChangeRepository.save(change);
						
					}
				}
				
				
				
				//Check the Deleted state
				if(fdTicket.getDeleted()!=null) { //there is a true or false value in the deleted field
					if(ticket.isDeleted()!=fdTicket.getDeleted()) {
						String oldValue=Boolean.toString(ticket.isDeleted());
						ticket.setDeleted(fdTicket.getDeleted());
						ticketRepository.save(ticket);
						
						TicketChange change=new TicketChange(
								fdTicket.getId(),
								ticket.getCreatedAt(),
								TicketChange.ChangeType.DeletedChange,
								oldValue,
								Boolean.toString(fdTicket.getDeleted())
							);
						
						ticketChangeRepository.save(change);
						
					}
				}
				
				
			}
		
		
		System.out.println("END SYNC "+counter+" succesfully");
	}
	
	
	
	private int getLevel(String name) {
		
		if(name.equals("Andre Reis"))return 4;
		if(name.equals("Glen De Scheerder"))return 4;
		if(name.equals("Damien Verlee"))return 4;
		if(name.equals("Iris Devroye"))return 4;
		if(name.equals("Julian Novales Flamarique"))return 4;
		
		
		if(name.equals("Paul Wijsen"))return 3;
		if(name.equals("Gent Binxhiu"))return 3;
		if(name.equals("Petrit Halili"))return 3;
		
		if(name.equals("Uma Devi"))return 2;
		if(name.equals("Victor Altamirano"))return 2;
		if(name.equals("Vijaya Vardhan"))return 2;
		
		return 0;
	}

	private void updateAgent(Ticket ticket,TicketJson fdTicket) {
		
		String oldvalue="";
		String newValue="";
		
		if(ticket.getAgent()!=null) {
			oldvalue=ticket.getAgent().getFreshdeskId();
		}
		
		
		Agent agent=agentRepository.findByFreshdeskId(fdTicket.getResponderId());
		ticket.setAgent(agent); //The agent must exist !!! (to be fixed later)
		newValue=agent.getFreshdeskId();
		System.out.println("Agent set to "+agent.getName());
		ticketRepository.save(ticket);

		TicketChange change=new TicketChange(
				fdTicket.getId(),
				ticket.getCreatedAt(),
				TicketChange.ChangeType.AgentChange,
				oldvalue,
				newValue
			);
		
		ticketChangeRepository.save(change);
	}
	
}
