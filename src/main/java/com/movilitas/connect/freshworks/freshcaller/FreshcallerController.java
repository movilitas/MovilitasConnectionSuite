package com.movilitas.connect.freshworks.freshcaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FreshcallerController {

	@Autowired
	FreshcallerService freshcallerService;
	
	@GetMapping("/freshcaller/test")
	public Object test()  {
		return  freshcallerService.test();
		//return null;
	}
	
	
	
}
