package com.movilitas.connect.freshworks.freshdesk.dao;

import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public abstract  interface AgentRepository extends CrudRepository<Agent, Integer>{

	Agent findByFreshdeskId(String freshDeskId);

	boolean existsByFreshdeskId(String agentId);

	
}
