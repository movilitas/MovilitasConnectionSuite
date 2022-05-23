package com.movilitas.connect.freshworks.freshcaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FreshcallerScheduler {
	
	private int counter=0;
	
	@Autowired
	FreshcallerService freshcallerService;
	
	//@Scheduled(fixedDelay=5000) 
	public void checkForAbandonedCalls(){
		counter++;
		System.out.println(counter+ ". START Checking for new Freshcaller Abandoned calls");
		boolean hasCall=freshcallerService.hasNewAbandonedCalls();
		
		
		System.out.println("END Checking for new Freshcaller Abandoned calls : "+hasCall);
	}
}
