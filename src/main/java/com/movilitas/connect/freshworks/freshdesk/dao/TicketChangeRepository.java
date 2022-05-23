package com.movilitas.connect.freshworks.freshdesk.dao;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.movilitas.connect.freshworks.freshdesk.dao.TicketChange.ChangeType;



@Repository
public abstract  interface TicketChangeRepository extends CrudRepository<TicketChange, Integer>{

	ArrayList<TicketChange> findAllByTimestampGreaterThanEqualOrderByTimestampAsc(Date date);

	TicketChange findByFreshdeskIdAndChangeType(String freshdeskId, ChangeType aChange);

	ArrayList<TicketChange> findByFreshdeskId(String freshdeskId);

	
}
