package com.movilitas.connect.freshworks.freshdesk;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.movilitas.connect.freshworks.freshdesk.dao.Agent;
import com.movilitas.connect.freshworks.freshdesk.dao.AgentRepository;
import com.movilitas.connect.freshworks.freshdesk.json.AgentJson;
import com.movilitas.connect.freshworks.freshdesk.json.TicketJson;


@Service
public class FreshdeskService {
	
	
	private HttpEntity<String> entity;
	private RestTemplate restTemplate;
	private String host="https://movilitas.freshdesk.com";
	
	@Autowired
	AgentRepository agentRepository;
	
	@EventListener(ApplicationReadyEvent.class)
	public void doAtStartup() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException{

		String apiKey="QcmLB25sJhr3nx2J067";
		
		String authString = Base64Utils.encodeToString(String.format("%s:%s", apiKey,"X").getBytes());
		
		TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
	    SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
	    Registry<ConnectionSocketFactory> socketFactoryRegistry =  RegistryBuilder.<ConnectionSocketFactory> create().register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();
	    BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager(socketFactoryRegistry);
	    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(connectionManager).build();
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	    restTemplate=new RestTemplate(requestFactory);
	    
	    HttpHeaders headers;
		headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.add("Authorization", authString);
	    entity = new HttpEntity<String>(headers);
	   
	    System.out.println("Application initialized. Connected to FreshDesk");

	}
	
	public TicketJson[] getTicketsUpdatedSince(LocalDate udated_at) {
		
		
		TicketJson[]  changedItems = getTicketsChangedSince(udated_at);
		TicketJson[]  deletedItems = getTicketsDeletedSince(udated_at);
		
		TicketJson[] both = ArrayUtils.addAll(changedItems, deletedItems);
		return both;
		
	}
	
	public TicketJson[] getTicketsChangedSince(LocalDate udated_at) {
		
		String strUrl="/api/v2/tickets?updated_since="+udated_at+"&per_page=100"; //include the deleted tickets
		return getTicketsJSONs(strUrl);
		
	}
	
	public TicketJson[] getTicketsDeletedSince(LocalDate udated_at) {
		String strUrl="/api/v2/tickets?updated_since="+udated_at+"&per_page=100&filter=deleted"; //include the deleted tickets
		return getTicketsJSONs(strUrl);
	}
	
	private TicketJson[] getTicketsJSONs(String strURL) {
		
		String URL=host+strURL;
		System.out.println("Calling "+URL);
		try {			
			ResponseEntity<TicketJson[]> response= restTemplate.exchange(URL, HttpMethod.GET, entity, TicketJson[].class);
			//ResponseEntity<Object> response= restTemplate.exchange(URL, HttpMethod.GET, entity, Object.class);
			//System.out.println("REPLY : "+response.getBody());
			//TicketJson[] jsons=(TicketJson[])response.getBody();
			
			return response.getBody();
		} catch (RestClientException e) {
			System.out.println("    RestClientException CONNECTING TO FRESHDESK "+URL);
			e.printStackTrace();
			return new TicketJson[0];
		}
	}
	
	public TicketJson getTicket(String reference) {

		String strUrl="/api/v2/tickets/"+reference;
		String URL=host+strUrl;
		//System.out.print("Calling "+URL);
		ResponseEntity<TicketJson> response= restTemplate.exchange(URL, HttpMethod.GET, entity, TicketJson.class);
		//System.out.println("    -   Call done.");
		TicketJson ticket=response.getBody();
		//System.out.println("Ticket "+ticket.getId()+" : isDeleted : "+ticket.getDeleted());
		
		return ticket;
		
	}
	
	public Object getTicketRaw(String reference) {

		String strUrl="/api/v2/tickets/"+reference;
		String URL=host+strUrl;
		System.out.print("Calling "+URL);
		ResponseEntity<TicketJson> response= restTemplate.exchange(URL, HttpMethod.GET, entity, TicketJson.class);
		//System.out.println("    -   Call done.");
		return response.getBody();
		
	}
	
	
	public AgentJson getAgentFromFreshdesk(String agentid) {

		String strUrl="/api/v2/agents/"+agentid;		
		String URL=host+strUrl;
		
		System.out.print("Calling "+URL);
		ResponseEntity<AgentJson> response= restTemplate.exchange(URL, HttpMethod.GET, entity, AgentJson.class);
		//System.out.println("    -   Call done");
		
		return response.getBody();
	}


	
	public Object getAllAgentsFromFreshdesk() {

		String strUrl="/api/v2/agents";
		String URL=host+strUrl;
		System.out.print("Calling "+URL);
		ResponseEntity<Object> response= restTemplate.exchange(URL, HttpMethod.GET, entity, Object.class);
		//System.out.println("    -   Call done");
		return response.getBody();
		
	}
	
//	public void syncAgents() {
//		ArrayList<AgentJson> allAgents=getAllAgentsFromFreshdesk();
//		for(AgentJson agentJson:allAgents) {
//			if(!agentRepository.existsByfreshdeskId(agentJson.id)) {
//				Agent newAgent=new Agent;
//				newAgent.freshdeskId=agentJson.id;
//				agentRepository.save(newAgent);
//			}
//			
//			Agent dbAgent=agentRepository.findByFreshdeskId(agentJson.id);
//			dbAgent.sync(agentJson);
//			
//		}
//		
//		//To DO :Clan DB from Agents that are not there anymore
//		
//		
//	}
	
	

	public String changeAgentLevel(String freshDeskId, int level) {
		Agent agent=agentRepository.findByFreshdeskId(freshDeskId);
		agent.setLevel(level);
		Agent savedAgent=agentRepository.save(agent);
		return "Agent "+savedAgent.getName()+" has been moved to level "+savedAgent.getLevel();
	}

	
	public Object test(){
		
		return "Hello Paul";
	}

	
	
	
}
