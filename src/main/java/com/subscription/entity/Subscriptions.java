package com.subscription.entity;

import java.util.ArrayList;

public class Subscriptions {

	String date;
	ArrayList<AddSubscription> plans = new ArrayList<>();
	ArrayList<TopUp>topUp = new ArrayList<>();
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public ArrayList<TopUp> getTopUp() {
		return topUp;
	}
	public void setTopUp(ArrayList<TopUp> topUp) {
		this.topUp = topUp;
	}
	public ArrayList<AddSubscription> getPlans() {
		return plans;
	}
	public void setPlans(ArrayList<AddSubscription> plans) {
		this.plans = plans;
	}
	
	
	

	

}
