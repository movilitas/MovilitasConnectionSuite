package com.movilitas.connect.freshworks.freshdesk;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.movilitas.connect.freshworks.freshdesk.dao.Agent;
import com.movilitas.connect.freshworks.freshdesk.dao.AgentRepository;
import com.movilitas.connect.freshworks.freshdesk.dao.Ticket;
import com.movilitas.connect.freshworks.freshdesk.dao.TicketChange;
import com.movilitas.connect.freshworks.freshdesk.dao.TicketChangeRepository;
import com.movilitas.connect.freshworks.freshdesk.dao.TicketRepository;
import com.movilitas.connect.freshworks.freshdesk.dao.TicketChange.ChangeType;
import com.movilitas.connect.freshworks.freshdesk.json.TicketJson;

import javassist.compiler.TypeChecker;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FreshdeskController {

	@Autowired
	FreshdeskService freshdeskService;
	
	@Autowired
	AgentRepository agentRepository;
	
	@Autowired
	TicketChangeRepository ticketChangeRepository;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@GetMapping("/freshdesk/tickets/{datecode}")
	public TicketJson[] getTicketsUpdatedSince(@PathVariable String datecode,HttpServletRequest request)  {
		System.out.println("INTO getTicketsUpdatedSince "+datecode);
		LocalDate udated_at = LocalDate.parse(datecode, DateTimeFormatter.BASIC_ISO_DATE);
		return  freshdeskService.getTicketsUpdatedSince(udated_at);
	}
	
	@GetMapping("/freshdesk/ticket/{referenceId}")
	public Object getTicket(@PathVariable String referenceId,HttpServletRequest request)  {
		System.out.println("INTO getTicket "+referenceId);
		return  freshdeskService.getTicket(referenceId);
	}
	
	@GetMapping("/freshdesk/ticket/raw/{referenceId}")
	public Object getTicketRaw(@PathVariable String referenceId,HttpServletRequest request)  {
		System.out.println("INTO getTicket Raw "+referenceId);
		return  freshdeskService.getTicketRaw(referenceId);
	}
	
	@GetMapping("/freshdesk/agent/{agentid}")
	public Object getAgent(@PathVariable String agentid)  {
		return freshdeskService.getAgentFromFreshdesk(agentid);
	}
	
	@GetMapping("/freshdesk/agents")
	public Object getAgents()  {
		return agentRepository.findAll();
	}
	
	@GetMapping("/freshdesk/ticketchanges/{datecode}")
	public Object getChangesSince(@PathVariable String datecode) throws ParseException  {
		
		Date date=new SimpleDateFormat("YYYYMMD").parse(datecode);  
		
		ArrayList<TicketChange> allChanges= ticketChangeRepository.findAllByTimestampGreaterThanEqualOrderByTimestampAsc(date);
		return allChanges;
	}
	
	@GetMapping("/freshdesk/agent/{freshDeskAgentId}/{level}")
	public Object changeAgentLevel(@PathVariable String freshDeskAgentId,@PathVariable int level)  {
		System.out.println("INTO changeAgentLevel.");
		return freshdeskService.changeAgentLevel(freshDeskAgentId, level);
	}
	
	@GetMapping("/freshdesk/migrate")
	public Object migrate(HttpServletRequest request) throws ParseException  {
		
		//Convert all tickets in the DB
		ArrayList<Ticket> allTickets=ticketRepository.findAll();
		int c=0;
		for(Ticket ticket:allTickets) {
			c++;
			System.out.println("Migrating "+c+"/"+allTickets.size());
			TicketJson json=freshdeskService.getTicket(ticket.getFreshdeskId()+" id:"+ticket.getFreshdeskId());

			
			//Change the "created at" date
			String strDate=json.getCreatedAt();
			ZonedDateTime zdt = ZonedDateTime.parse(strDate);
			LocalDateTime ldt = zdt.toLocalDateTime();
			ticket.setCreatedAt(java.sql.Timestamp.valueOf(ldt));
			ticketRepository.save(ticket);
			
			ArrayList<TicketChange> tcs = ticketChangeRepository.findByFreshdeskId(ticket.getFreshdeskId());
			for(TicketChange tc:tcs) {
				tc.setTicketCreationTimestamp(ticket.getCreatedAt());
				ticketChangeRepository.save(tc);
			}
			
			
			
//			//Sync Deleted status 
//			if(json.getDeleted()!=null) {//there is a value
//				ticket.setDeleted(json.getDeleted());
//				ticketRepository.save(ticket);
//			}
//			
//			
//			//Sync ticketType field
//			if(json.getType()!=null) { //there is a type specified
//				String ticketType=json.getType();
//				ticket.setTicketType(ticketType);
//				ticketRepository.save(ticket);
//			}
//			
//			//Add a ticketTypeChange for every ticket in the DB if not yet there.
//			TicketChange aTypeChange=ticketChangeRepository.findByFreshdeskIdAndChangeType(ticket.getFreshdeskId(), ChangeType.TicketTypeChange);
//			if(aTypeChange==null) { //not yet in the bd
//				TicketChange tc=new TicketChange();
//				tc.setChangeType(ChangeType.TicketTypeChange);
//				tc.setFreshdeskId(ticket.getFreshdeskId());
//				tc.setNewValue(ticket.getTicketType());
//				tc.setTimestamp(new Date());
//				ticketChangeRepository.save(tc);
//			}
//			
//			
//			
//		}
//		
//		//Set for all deleted items the deleteChange as last
//		ArrayList<Ticket> deletedTickets=ticketRepository.findAllByIsDeleted(true);
//		for(Ticket t:deletedTickets) {
//			String freshdeskId=t.getFreshdeskId();
//			
//			TicketChange change=ticketChangeRepository.findByFreshdeskIdAndChangeType(freshdeskId,TicketChange.ChangeType.DeletedChange);
//			if(change!=null) { //it has been deleted. Create a new Change to have this as last.
//				TicketChange newChange=new TicketChange();
//				newChange.setChangeType(TicketChange.ChangeType.DeletedChange);
//				newChange.setFreshdeskId(change.getFreshdeskId());
//				newChange.setNewValue(change.getNewValue());
//				newChange.setOldValue(change.getOldValue());
//				newChange.setTimestamp(new Date());
//				
//				ticketChangeRepository.delete(change);
//				ticketChangeRepository.save(newChange);
//				System.out.println("Storing IsDeleteChanged for "+freshdeskId);
//				
//			}else { //deleted item that has no change yet
//				TicketChange tc=new TicketChange();
//				tc.setChangeType(ChangeType.DeletedChange);
//				tc.setFreshdeskId(t.getFreshdeskId());
//				tc.setNewValue("true");
//				tc.setTimestamp(new Date());
//				ticketChangeRepository.save(tc);
//			}
		}
		
		
		
		return "Migratopn Done";
	}
	
}
