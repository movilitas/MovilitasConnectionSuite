package com.movilitas.connect.freshworks.freshcaller;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.net.ssl.SSLContext;

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
import org.springframework.web.client.RestTemplate;

import com.movilitas.connect.freshworks.freshcaller.json.Call;
import com.movilitas.connect.freshworks.freshcaller.json.FreshcallerCalls;
import com.movilitas.connect.freshworks.freshcaller.json.Participant;


@Service
public class FreshcallerService {
	
	
	HttpEntity<String> entity;
	private RestTemplate restTemplate;
	
	
//	@EventListener(ApplicationReadyEvent.class)
	public void doAtStartup() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException{

		String apiKey="e6e5be5153273569431cb1df108f94a9";
		HttpHeaders headers;
		
		//Configuration of Monday restTemplate
		TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
	    SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
	    Registry<ConnectionSocketFactory> socketFactoryRegistry =  RegistryBuilder.<ConnectionSocketFactory> create().register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();
	    BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager(socketFactoryRegistry);
	    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(connectionManager).build();
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		headers = new HttpHeaders();
		//headers.set("Host", "movilitas.freshcaller.com");
		headers.set("Accept", "application/json");
		headers.set("X-Api-Auth", apiKey);
	    restTemplate=new RestTemplate(requestFactory);
		
	    entity = new HttpEntity<String>("",headers);
	   
	    
	    System.out.println("Connected to FreshCaller");
	    
		
		
	}

	public boolean hasNewAbandonedCalls() {
		
		return false;
	}
	
	
	public FreshcallerCalls getAllCalls() {
		String URL="https://movilitas.freshcaller.com/api/v1/calls?per_page=100";
		ResponseEntity<FreshcallerCalls> response= restTemplate.exchange(URL, HttpMethod.GET, entity, FreshcallerCalls.class);
		return response.getBody();
		
	
		
	}
	
	private Object getAll(String objectType) {
		String URL="https://movilitas.freshcaller.com/api/v1/"+objectType+"?per_page=100";
		ResponseEntity<Object> response= restTemplate.exchange(URL, HttpMethod.GET, entity, Object.class);
		return response.getBody();
	}
	
	private TreeSet<String> getTeams() {
		
		TreeSet<String> teamIDs=new TreeSet<String>();
		
		int counter=1;

		for(Call call:getAllCalls().getCalls()) {
			
			
			if(call.getAssignedTeamId()!=null)teamIDs.add(call.getAssignedTeamId());
			
			if(!call.getDirection().equals("incoming"))continue;
			if(call.getAssignedCallQueueName()==null)continue;
			
			if(!call.getAssignedCallQueueName().equals("Hypercare Digital Manufacturing EU") && !call.getAssignedCallQueueName().equals("Simple_message_+3278480209")) continue;
			Participant p=call.getParticipants().get(0);
			int status=p.getCallStatus();
			if(status==17) {
				System.out.println("Abandoned Call "+counter+" : From "+p.getCallerNumber()+" to "+call.getAssignedCallQueueName()+" on "+call.getCreatedTime());
				counter++;
			}
		}
		
		System.out.println("TEAMS QTY : "+teamIDs.size());
		for(String teamID:teamIDs) {
			
			String URL="https://movilitas.freshcaller.com/api/v1/teams/"+teamID;
			System.out.println("REQUESTING "+URL);
			ResponseEntity<Object> response= restTemplate.exchange(URL, HttpMethod.GET, entity, Object.class);
			System.out.println("\n TEAM "+teamID+" READ : \n"+response.getBody());
		}
		return teamIDs;
	}
	
	
	
	public Object test(){
//		
		//return getAllCalls();
		return getTeams();

	}
	
	

	
	
	
	
}
