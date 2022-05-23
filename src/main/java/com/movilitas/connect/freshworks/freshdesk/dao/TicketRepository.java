package com.movilitas.connect.freshworks.freshdesk.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public abstract  interface TicketRepository extends CrudRepository<Ticket, Integer>{

	Ticket findByFreshdeskId(String id);

	
	ArrayList<Ticket> findAllByIsDeleted(boolean b);
	@Override
	ArrayList<Ticket> findAll();
	
}
