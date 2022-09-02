package com.subscription.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subscription.Exceptions.AddSubscriptionFailed;
import com.subscription.entity.ReneValRemainder;
import com.subscription.entity.Subscriptions;
import com.subscription.services.SubscriptionService;

@RestController
public class SubscriptionController {
	
	@Autowired
	SubscriptionService service;

	@RequestMapping("/subscribe")
   public ResponseEntity<ArrayList<ReneValRemainder>> addSubscription(@RequestBody Subscriptions subs ) {
	    return new ResponseEntity<>(service.addSubscription(subs), HttpStatus.OK);
   }
	
	
	@ExceptionHandler(AddSubscriptionFailed.class)
	 public ResponseEntity<String> exceptionHandler(AddSubscriptionFailed exception) {
		return new ResponseEntity<>(exception.getMessage() + (exception.getReason() == null? "" :exception.getReason())  , HttpStatus.BAD_REQUEST);
	}
   
   
	
	
}
